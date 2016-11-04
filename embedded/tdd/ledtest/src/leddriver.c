#include "leddriver.h"
#include "runtimeerror.h"

const uint16_t ALL_LEDS_ON = ~0;
const uint16_t ALL_LEDS_OFF = 0;

const int FIRST_LED = 1;
const int LAST_LED = 16;

void LedDriver_Create(uint16_t * address) {
}

void LedDriver_Destroy(void) {
}

void LedDriver_TurnOn(int ledNumber) {
}

void LedDriver_TurnOff(int ledNumber) {
}

void LedDriver_TurnAllOn(void) {
}

void LedDriver_TurnAllOff(void) {
}

bool LedDriver_IsOn(int ledNumber) {
	return false;
}

bool LedDriver_IsOff(int ledNumber) {
	return false;
}

