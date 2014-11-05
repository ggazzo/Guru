/* Código gerado automaticamente pelo GuruParser*/
#include Sensor.h
Sensor::Sensor (int echo, int trig, int pulse){ 
 }
void Sensor::exec (){ 
toogleDelay (10);
duration = pulseIn (this->echo, HIGH, MAX_TEMP);
distance = (duration / 2) / 29.1;
distance = (distance < DISTANCIA_MINIMA ? DISTANCIA_MINIMA : distance);
if(distance > DISTANCIA_MAXIMA || ! duration){
analogWrite (this->pulse, 0);
if(true){

}else{

}
}vibracao = VIBRACAO_MAXIMA - (distance - DISTANCIA_MINIMA) * VIBRACAO_RANGE / DISTANCIA_RANGE;
analogWrite (this->pulse, vibracao);

 }
void Sensor::exec2 (int a){ 

 }
toogleDelay Sensor::delay (){ 
digitalWrite (this->trig, HIGH);
delayMicroseconds (delay);
digitalWrite (this->trig, LOW);

 }
