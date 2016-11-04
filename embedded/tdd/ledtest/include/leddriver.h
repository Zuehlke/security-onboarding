#ifndef LEDDRIVER_H
#define LEDDRIVER_H

#include <stdbool.h>
#include <stdint.h>

extern const uint16_t ALL_LEDS_ON;
extern const uint16_t ALL_LEDS_OFF;

extern const int FIRST_LED ;
extern const int LAST_LED;

void LedDriver_Create(uint16_t * address);
void LedDriver_Destroy(void);

void LedDriver_TurnOn(int ledNumber);
void LedDriver_TurnOff(int ledNumber);
void LedDriver_TurnAllOn(void);
void LedDriver_TurnAllOff(void);

bool LedDriver_IsOn(int ledNumber);
bool LedDriver_IsOff(int ledNumber);

#endif
