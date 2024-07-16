#include <Arduino.h>
#include "leitorQRCode.h"
#include <ota.h>
#include <globais.h>
#include "esp_task_wdt.h"
#include <sdcard.h>
#include <ArduinoJson.h>
#include <LiquidCrystal_I2C.h>

#define DISPLAY_ADDR 0x27
#define SDA_PIN 21
#define SCL_PIN 22

LiquidCrystal_I2C lcd(DISPLAY_ADDR, 16, 2);

// Parâmetros da rede WiFi
String ssid = "Silvio de Oliveira";
String password = "02062003";
String myTimezone = "WET0WEST,M3.5.0/1,M10.5.0";

Cliente cliente1(0, "", "");
Cliente cliente2(0, "", "");

void setup()
{
  WRITE_PERI_REG(RTC_CNTL_BROWN_OUT_REG, 0); // Desabilita o detector de brownout

  Wire.begin(SDA_PIN, SCL_PIN);

  Serial.begin(115200);
  Serial.println();
  // Serial.setDebugOutput(true);

  // Configura o pino do LED do ESP como saída
  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, LOW);

  // Configurando acesso ao WiFi
  setupWifi();

  delay(2000);

  // Configurando a página Web
  setupWeb();

  delay(2000);

  // Configurando cartão SD
  setupSDCard();

  // Initialize time with timezone
  initTime(myTimezone);

  // Inicializa o watchdog timer com um timeout de 10 segundos
  esp_task_wdt_init(10, true);
  esp_task_wdt_add(NULL); // Adiciona o loopTask ao watchdog timer

  // Configurando câmera e task para ler QR Code
  setupCamera();
  createQRCodeReaderTask();

  // Inicializa o I2C com os pinos definidos
  Wire.begin(SDA_PIN, SCL_PIN);

  // Inicializa o display
  lcd.init();
  lcd.backlight(); // Liga a luz de fundo
}

void loop()
{
  taskUpdate();
}

void CheckPassCode(const struct quirc_data *data)
{
  String passCode = (const char *)data->payload; // Verificar se o que tem no QR Code tem no cartão SD

  switch (passCode)
  {
  case cliente1.passCode:

    digitalWrite(cliente1.id, HIGH);

    Serial.println("Abriu a gaveta 1");
    delay(1000);

    SaveSDCard();

    lcd.setCursor(0, 0);
    lcd.print(cliente1.name + ", retire seu produto.");

    break;
  case cliente2.passCode:

    digitalWrite(cliente2.id, HIGH);
    digitalWrite(cliente1.id, HIGH);
    digitalWrite(LED_PIN, HIGH);

    Serial.println("Abriu a gaveta 2");

    SaveSDCard();

    lcd.setCursor(0, 0);
    lcd.print(cliente2.name + ", retire seu produto.");

    break;
  default:
    break;
  }

  // Comparando o conteúdo de data->payload com a string desejada
  if (passCode.equals("oii"))
  {
    Serial.println("Abriu a gaveta 1");

    SaveSDCard();
  }
  else
  {
    Serial.println("QR Code inválido!");
  }

  QRCodeResult = (const char *)data->payload;
}

//------------------------------------------------cartão sd----------------------------------------------------

void setTimezone(String timezone)
{
  Serial.printf("  Setting Timezone to %s\n", timezone.c_str());
  setenv("TZ", timezone.c_str(), 1); //  Now adjust the TZ.  Clock settings are adjusted to show the new local time
  tzset();
}

void initTime(String timezone)
{
  struct tm timeinfo;
  Serial.println("Setting up time");
  configTime(0, 0, "pool.ntp.org"); // First connect to NTP server, with 0 TZ offset
  if (!getLocalTime(&timeinfo))
  {
    Serial.println(" Failed to obtain time");
    return;
  }
  Serial.println("Got the time from NTP");
  // Now we can set the real timezone
  setTimezone(timezone);
}

String getPictureFilename()
{
  struct tm timeinfo;
  if (!getLocalTime(&timeinfo))
  {
    Serial.println("Failed to obtain time");
    return "";
  }
  char timeString[20];
  strftime(timeString, sizeof(timeString), "%Y-%m-%d_%H-%M-%S", &timeinfo);
  Serial.println(timeString);
  String filename = "/picture_" + String(timeString) + ".jpg";
  return filename;
}

void SaveSDCard()
{
  digitalWrite(LED_PIN, HIGH);

  // Take Picture with Camera
  camera_fb_t *fb = esp_camera_fb_get();

  digitalWrite(LED_PIN, LOW);

  if (!fb)
  {
    Serial.println("Camera capture failed");
    delay(1000);
    ESP.restart();
  }

  String path; // Path where new picture will be saved in SD Card (name of file)

  path = getPictureFilename();

  // Se estiver vazio, significa que não pegou o tempo corretamente
  if (path == "")
  {
    path = "/picture_unknown_time.jpg";
  }

  Serial.printf("Picture file name: %s\n", path.c_str());

  // Save picture to microSD card
  fs::FS &fs = SD_MMC;
  File file = fs.open(path.c_str(), FILE_WRITE);
  if (!file)
  {
    Serial.printf("Failed to open file in writing mode");
  }
  else
  {
    file.write(fb->buf, fb->len); // payload (image), payload length
    Serial.printf("Saved: %s\n", path.c_str());
  }
  file.close();
  esp_camera_fb_return(fb);

  delay(1000);
}

//-----------------------------------------------EEPROM-------------------------------------------------------

void updateClients()
{
  unsigned int i = 0;
  while (data[i] != '\0')
  {
    writeEEPROM(eeaddress + i, data[i]);
    i++;
  }
  writeEEPROM(eeaddress + i, '\0'); // Adiciona o caractere nulo de terminação
}

void getClients()
{

}

//------------------------------------------------WiFi-------------------------------------------------------

void createAP()
{
  WiFi.mode(WIFI_AP_STA); // Comfigura o ESP32CAM como ponto de acesso e estação

  WiFi.softAP("ESP32-CAM", "1234sksksk");

  // Obtém o endereço IP do ponto de acesso
  IPAddress IP = WiFi.softAPIP();
  Serial.print("Endereço IP do AP: ");
  Serial.println(IP);
}

void setupWifi()
{
  WiFi.begin(ssid, password); // inicia a conexão com o WiFi

  uint8_t contador_wifi = 0;

  // Wait for connection
  while (WiFi.status() != WL_CONNECTED)
  {
    contador_wifi++;
    if (contador_wifi == 4) // 4 segundos
    {
      Serial.println("Sem conexão WiFi. Gerando AP (Access Point)...");
      createAP();
      break;
    }
    delay(500);
  }

  if (WiFi.status() == WL_CONNECTED)
  {
    Serial.print("Conectado em ");
    Serial.println(ssid);
  }
}

void drawer1Function()
{
  Serial.println("Drawer 1 opened!");
}

void drawer2Function()
{
  Serial.println("Drawer 2 opened!");
}

//-------------------------------------------Tasks de tempo--------------------------------------------------

void taskUpdate()
{
  static uint16_t _tic1000ms = 0, _tic500ms = 0, _tic250ms = 0;
  static uint8_t _tic10ms = 0, _tic25ms = 0, _tic50ms = 0;
  static uint32_t updateTime = 0;
  uint32_t currentMillis = millis();
  uint32_t elapsedTime = 0;

  if (updateTime == 0)
  {
    updateTime = currentMillis;
  }
  elapsedTime = currentMillis - updateTime;
  if (elapsedTime > 4) // 5ms
  {

    updateTime = currentMillis;
    task_5ms();

    _tic10ms += elapsedTime;
    _tic25ms += elapsedTime;
    _tic50ms += elapsedTime;
    _tic250ms += elapsedTime;
    _tic500ms += elapsedTime;
    _tic1000ms += elapsedTime;

    if (_tic10ms > 9)
    {
      _tic10ms -= 10;
      task_10ms();
    }
    if (_tic25ms > 24)
    {
      _tic25ms -= 25;
      task_25ms();
    }
    if (_tic50ms > 49)
    {
      _tic50ms -= 50;
      task_50ms();
    }

    if (_tic250ms > 249)
    {
      _tic250ms -= 250;
      task_250ms();
    }
    if (_tic500ms > 499)
    {
      _tic500ms -= 500;
      task_500ms();
    }
    if (_tic1000ms > 999)
    {
      _tic1000ms -= 1000;
      task_1000ms();
    }
  }
}

void task_5ms()
{
}

void task_10ms()
{
}

void task_25ms()
{
}

// Server
void task_50ms()
{
  server.handleClient(); // manipula clientes conectados na Web
}

void task_250ms()
{
}

void task_500ms()
{
}

// WDT
void task_1000ms()
{
  // Reseta o watchdog timer
  esp_task_wdt_reset();
}