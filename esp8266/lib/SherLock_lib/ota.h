#ifndef __ota__
#define __ota__

#include <WebServer.h>
#include <Update.h>
#include <globais.h>

#define PASSWORD_OTA "1234"
#define VERSAO_FIRMWARE "02/06/2024"

// Parâmetros de rede
IPAddress local_ip(192, 168, 0, 121);
IPAddress gateway(192, 168, 0, 1);
IPAddress subnet(255, 255, 255, 0);
const uint32_t PORTA = 80; // A porta que será utilizada (padrão 80)

boolean OTA_AUTORIZADO = false; // Sinalizador de autorização do OTA
WebServer server(PORTA);        // inicia o servidor na porta selecionada, aqui testamos na porta 3000, ao invés da 80 padrão

// Inicializa o OTA
void setupWeb(void);

// Abre a gaveta 1 quando clicar no botão "Abrir gaveta 1" no site
void drawer1Function(void);
// Abre a gaveta 2 quando clicar no botão "Abrir gaveta 2" no site
void drawer2Function(void);

// Páginas HTML utilizadas no procedimento OTA
String verifica = "<!DOCTYPE html><html><head><title>ESP32CAM webOTA</title><meta charset='UTF-8'><style>body{background-color:black;color:white;text-align:center;font-family:Arial,sans-serif;}h1{margin-top:20px;}form{margin-top:100px;display:inline-block;text-align:center;}label,input{display:block;margin:10px auto;}input[type='submit']{background-color:#4CAF50;color:white;border:none;padding:15px 32px;text-align:center;text-decoration:none;display:inline-block;font-size:16px;margin:20px auto;cursor:pointer;border-radius:4px;}input[type='file'],input[type='text']{padding:10px;margin:10px auto;border-radius:4px;border:1px solid #ccc;width:300px;max-width:100%;}a{color:white;text-decoration:none;}#siteName{position:absolute;bottom:10px;right:10px;color:white;}</style></head><body><h1>ESP32CAM webOTA</h1><form method='POST' action='/avalia' enctype='multipart/form-data'><label style='color:white;'>Autorização:</label><input type='text' name='autorizacao'><input type='submit' value='Ok'></form><div id='siteName'><p style='color:white;'>Para mais informações, acesse: <a href='http://www.sherlock.com' style='text-decoration:underline;'>www.sherlock.com</a></p></div></body></html>";
String serverIndex = R"(
<!DOCTYPE html>
<html>
<head>
    <title>ESP32CAM webOTA</title>
    <meta charset='UTF-8'>
    <style>
        body{
            background-color:black;
            color:white;
            text-align:center;
            font-family:Arial,sans-serif;
        }
        h1{
            margin-top:20px;
        }
        form{
            margin-top:100px;
            display:inline-block;
            text-align:center;
        }
        label,input{
            display:block;
            margin:10px auto;
        }
        input[type='submit']{
            background-color:#4CAF50;
            color:white;
            border:none;
            padding:15px 32px;
            text-align:center;
            text-decoration:none;
            display:inline-block;
            font-size:16px;
            margin:20px auto;
            cursor:pointer;
            border-radius:4px;
        }
        .button-container{
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
        }
        .button-container button{
            background-color:#4CAF50;
            color:white;
            border:none;
            padding:15px 32px;
            text-align:center;
            text-decoration:none;
            font-size:16px;
            cursor:pointer;
            border-radius:4px;
        }
        input[type='file'],input[type='text'],input[type='password']{
            padding:10px;
            margin:10px auto;
            border-radius:4px;
            border:1px solid #ccc;
            width:300px; /* Tamanho padrão */
            max-width:100%;
            box-sizing: border-box; /* Incluindo o padding na largura total */
        }
        a{
            color:white;
            text-decoration:none;
        }
        #siteName{
            position:absolute;
            bottom:10px;
            right:10px;
            color:white;
        }
    </style>
    <script>
        function sendRequest(url) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", url, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200)
                    alert(xhr.responseText);
            }
            xhr.send();
        }

        function openDrawer1() {
            sendRequest("/gaveta1");
        }

        function openDrawer2() {
            sendRequest("/gaveta2");
        }

        window.onload = function() {
            document.getElementById("button1").onclick = openDrawer1;
            document.getElementById("button2").onclick = openDrawer2;
        }
    </script>
</head>
<body>
    <h1>ESP32CAM webOTA</h1>
    <form method='POST' action='/update' enctype='multipart/form-data'>
        <label for='file' style='color:white;'>Arquivo:</label>
        <input type='file' name='update'>
        <input type='submit' value='Atualizar'>
        <label for='ssid' style='color:white;'>SSID:</label>
        <input type='text' name='ssid' placeholder='Digite o SSID'>
        <label for='password' style='color:white;'>Senha:</label>
        <input type='password' name='password' placeholder='Digite a senha' style='width: 300px;'>
        <button type='submit' style='background-color:#4CAF50;color:white;border:none;padding:15px 32px;text-align:center;text-decoration:none;display:inline-block;font-size:16px;margin:20px auto;cursor:pointer;border-radius:4px;'>Salvar</button>
        <div class="button-container">
            <button type='button' id='button1'>Abrir gaveta 1</button>
            <button type='button' id='button2'>Abrir gaveta 2</button>
        </div>
    </form>
    <div id='siteName'>
        <p style='color:white;'>Para mais informações, acesse: <a href='http://www.sherlock.com' style='text-decoration: underline;'>www.sherlock.com</a></p>
    </div>
</body>
</html>
)";
String Resultado_Ok = "<!DOCTYPE html><html><head><title>ESP32CAM webOTA</title><meta charset='UTF-8'><style>body{background-color:black;color:white;text-align:center;font-family:Arial,sans-serif;position:relative;height:100vh;display:flex;flex-direction:column;justify-content:center}h1{margin-top:20px}h2{margin:auto}#siteName{position:absolute;bottom:10px;right:10px}</style></head><body><h1>ESP32CAM webOTA</h1><h2>Atualização bem sucedida!</h2><div id='siteName'><p style='color:white;'>Para mais informações, acesse: <a href='http://www.sherlock.com' style='text-decoration:underline;'>www.sherlock.com</a></p></div></body></html>";
String Resultado_Falha = R"(
<!DOCTYPE html>
<html>
<head>
    <title>ESP32CAM webOTA</title>
    <meta charset='UTF-8'>
    <style>
        body {
            background-color: black;
            color: white;
            text-align: center;
            font-family: Arial, sans-serif;
            position: relative;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        h1 {
            margin-top: 20px;
        }

        #siteName {
            position: absolute;
            bottom: 10px;
            right: 10px;
        }

        #backButton {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        #backButton:hover {
            background-color: #555;
        }

        #failureMessage {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h2 id='failureMessage'>Falha durante a atualização. A versão anterior do firmware será recarregada.</h2>
    <form action='/serverIndex'>
        <input id='backButton' type='submit' value='Voltar à seleção de firmware'>
    </form>
    <div id='siteName'>
        <p style='color:white;'>Para mais informações, acesse: <a href='http://www.sherlock.com' style='text-decoration:underline;'>www.sherlock.com</a></p>
    </div>
</body>
</html>
)";

void setupWeb()
{
    server.on("/", HTTP_GET, []() // atende uma solicitação para a raiz
              {
      server.sendHeader("Connection", "close");
      server.send(200, "text/html", verifica); });

    // atende uma solicitação para a página avalia
    server.on("/avalia", HTTP_POST, []()
              {
      Serial.println("Em server.on /avalia: args= " + String(server.arg("autorizacao"))); //somente para debug

      if (server.arg("autorizacao") != PASSWORD_OTA) // confere se o dado de autorização atende a avaliação
      {
        //se não atende, serve a página indicando uma falha
        server.sendHeader("Connection", "close");
        server.send(200, "text/html", Resultado_Falha);
        //ESP.restart();
      }
      else
      {
        //se atende, solicita a página de índice do servidor
        // e sinaliza que o OTA está autorizado
        OTA_AUTORIZADO = true;
        server.sendHeader("Connection", "close");
        server.send(200, "text/html", serverIndex);
      } });

    // serve a página de indice do servidor
    // para seleção do arquivo
    server.on("/serverIndex", HTTP_GET, []()
              {
      server.sendHeader("Connection", "close");
      server.send(200, "text/html", serverIndex); });

    // tenta iniciar a atualização . . .
    server.on("/update", HTTP_POST, []()
              {
      //verifica se a autorização é false.
      //Se for falsa, serve a página de erro e cancela o processo.
      if (OTA_AUTORIZADO == false)
      {
        server.sendHeader("Connection", "close");
        server.send(200, "text/html", Resultado_Falha);
        return;
      }
      
      //Serve uma página final que depende do resultado da atualização
      server.sendHeader("Connection", "close");
      server.send(200, "text/html", (Update.hasError()) ? Resultado_Falha : Resultado_Ok);
      delay(1000);
      ESP.restart(); }, []()
              {
      //Mas estiver autorizado, inicia a atualização
      HTTPUpload& upload = server.upload();
      if (upload.status == UPLOAD_FILE_START)
      {
        Serial.setDebugOutput(true);
        Serial.printf("Atualizando: %s\n", upload.filename.c_str());
        if (!Update.begin())
        {
          //se a atualização não iniciar, envia para serial mensagem de erro.
          Update.printError(Serial);
        }
      }
      else if (upload.status == UPLOAD_FILE_WRITE)
      {
        if (Update.write(upload.buf, upload.currentSize) != upload.currentSize)
        {
          //se não conseguiu escrever o arquivo, envia erro para serial
          Update.printError(Serial);
        }
      }
      else if (upload.status == UPLOAD_FILE_END)
      {
        if (Update.end(true))
        {
          //se finalizou a atualização, envia mensagem para a serial informando
          Serial.printf("Atualização bem sucedida! %u\nReiniciando...\n", upload.totalSize);
        }
        else
        {
          //se não finalizou a atualização, envia o erro para a Serial.
          Update.printError(Serial);
        }
        Serial.setDebugOutput(false);
      }
      else
      {
        //se não conseguiu identificar a falha no processo, envia uma mensagem para a serial
        Serial.printf("Atualização falhou inesperadamente! (possivelmente a conexão foi perdida.): status=%d\n", upload.status);
      } });

    // Handle button 1 click
    server.on("/gaveta1", HTTP_GET, []()
              {
        drawer1Function();
        server.sendHeader("Connection", "close");
        server.send(200, "text/plain", "Drawer 1 opened!"); });

    // Handle button 2 click
    server.on("/gaveta2", HTTP_GET, []()
              {
        drawer2Function();
        server.sendHeader("Connection", "close");
        server.send(200, "text/plain", "Drawer 2 opened!"); });
        
    server.begin(); // inicia o servidor

    // Envia para a serial o IP atual do ESP
    Serial.print("Servidor em: ");
    Serial.println(WiFi.localIP().toString() + ":" + PORTA);
}

#endif