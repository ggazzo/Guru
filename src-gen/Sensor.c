
/* Código gerado automaticamente pelo GuruParser*/
#include Sensor.h
Sensor::~Sensor (){ 
 }
Sensor::Sensor (int echo, int trig, int pulse){ 
    this->echo = echo;this->trig = trig;this->pulse = pulse;pinMode (this->trig, OUTPUT);pinMode (this->echo, INPUT);pinMode (this->pulse, OUTPUT);digitalWrite (this->trig, LOW);
 }
void Sensor::exec (){ 
    toogleDelay (10);duration = pulseIn (this->echo, HIGH, MAX_TEMP);distance = (duration / 2) / 29.1;distance = (distance < DISTANCIA_MINIMA ? DISTANCIA_MINIMA : distance);if(distance > DISTANCIA_MAXIMA || ! duration){
        analogWrite (this->pulse, 0);return a;
    }
    vibracao = VIBRACAO_MAXIMA - (distance - DISTANCIA_MINIMA) * VIBRACAO_RANGE / DISTANCIA_RANGE;analogWrite (this->pulse, vibracao);
 }
void Sensor::exec2 (int a){ 
    
 }
void Sensor::toogleDelay (int delay){ 
    digitalWrite (this->trig, HIGH);delayMicroseconds (delay);digitalWrite (this->trig, LOW);
 }