
int data;
#include <FastLED.h>
#define led_pin 9
#define leds 3
CRGB led[leds];



typedef struct {
  int R;
  int G;
  int B;
} color;

color Color = {0, 0, 0};



void setup() {
  // put your setup code here, to run once:
  Serial.begin(19200);

  //pinMode(out, OUTPUT);
  //pinMode(out1, OUTPUT);
  //pinMode(out2, OUTPUT);
  pinMode(LED_BUILTIN, OUTPUT);

  FastLED.addLeds<WS2812B, led_pin, GRB>(led, leds);
  FastLED.setBrightness(50);

  while (!Serial) {
    ;
  }
  while (int meta = Serial.read() < 0) {
    ;
  }
  while (int meta = Serial.read() < 0) {
    ;
  }

}

void loop() {
  // put your main code here, to run repeatedly:


  byte sel;
  sel = Serial.read();
  if (sel > -1) {
    switch (sel) {
      case 1:
        Color.R = Serial.read();
        while (Color.R < 0) {
          Color.R = Serial.read();

          delay(10);
        }

        break;

      case 2:
        Color.G = Serial.read();
        while (Color.G < 0) {
          Color.G = Serial.read();
          delay(10);
        }

        break;


      case 3:
        Color.B = Serial.read();
        while (Color.B < 0) {
          Color.B = Serial.read();
          delay(10);
        }

        break;
    }
    for (int i = 0; i < 3; i++) {
      led[i] = CRGB(Color.R+128, Color.G+128 , Color.B+128);
    }
    FastLED.show();


  }
}
