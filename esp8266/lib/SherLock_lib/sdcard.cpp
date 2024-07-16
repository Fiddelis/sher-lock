#include <sdcard.h>

void setupSDCard()
{
  if (!SD_MMC.begin())
  {
    Serial.println("SD Card Mount Failed");
    return;
  }
  uint8_t cardType = SD_MMC.cardType();
  if (cardType == CARD_NONE)
  {
    Serial.println("No SD Card attached");
    return;
  }
  Serial.println("SD Card Ok");
}

