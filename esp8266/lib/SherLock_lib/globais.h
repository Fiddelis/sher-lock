#ifndef GLOBAIS_H
#define GLOBAIS_H

#include <Arduino.h>

class Cliente
{
public:
  // Construtor para inicializar os dados do cliente
  Cliente(int id, const char *nome, const char *passCode)
  {
    this->id = id;
    setNome(nome);
    setPassCode(passCode);
  }

  // Funções para acessar os dados do cliente
  int getId()
  {
    return id;
  }

  const char *getNome()
  {
    return nome;
  }

  const char *CheckPassCode()
  {
    return passCode;
  }

  // Funções para atualizar os dados do cliente
  void setId(int id)
  {
    this->id = id;
  }

  void setNome(const char *nome)
  {
    strncpy(this->nome, nome, sizeof(this->nome) - 1);
    this->nome[sizeof(this->nome) - 1] = '\0'; // Garante que a string seja terminada com null
  }

  void setPassCode(const char *passCode)
  {
    strncpy(this->passCode, passCode, sizeof(this->passCode) - 1);
    this->passCode[sizeof(this->passCode) - 1] = '\0'; // Garante que a string seja terminada com null
  }

private:
  uint8_t id;            // ID do cliente
  char nome[50];     // Nome do cliente
  char passCode[21]; // PassCode do cliente (20 caracteres + null terminator)
};

// -------------------FUNCTIONS-------------------

// Setup for WiFi connection
void setupWifi(void);

// Setup for SD Card
void SetupSD(void);

// Motores de tempo
void task_5ms(void);
void task_10ms(void);
void task_25ms(void);
void task_50ms(void);
void task_250ms(void);
void task_500ms(void);
void task_1000ms(void);
void taskUpdate(void);

// Obtém os clientes (id, nome e passcode) da EEPROM
void getClients(void);

// Atualiza dados dos clientes conforme salvo na EEPROM (id, nome e passcode)
void updateClients(void);

// Get the picture filename based on the current time
String getPictureFilename(void);

// Connect to NTP server and adjust timezone
void initTime(String timezone);

// Function to set timezone
void setTimezone(String timezone);

// -------------------MACROS-------------------

#define CS_SD D8 // Chip Select SD Card (D8)

// -----------------CONSTANTS-----------------


// -----------------VARIABLES-----------------


#endif
