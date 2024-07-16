#ifndef __sdcard__
#define __sdcard__

#include "FS.h"                // SD Card ESP32
#include "SD_MMC.h"            // SD Card ESP32
#include "time.h"
#include "soc/soc.h"
#include "driver/rtc_io.h"
#include <globais.h>

//-----------------------------------PROTÓTIPOS------------------------------

// Inicializa o cartão SD
void setupSDCard(void);

// Salva cartão SD
void SaveSDCard(void);

// Pega o nome do arquivo que salvou a foto
String getPictureFilename(void);

void initTime(String timezone);

#endif