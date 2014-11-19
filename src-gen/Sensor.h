
/* Código gerado automaticamente pelo GuruParser*/
#ifndef SENSOR_H
#define SENSOR_H
class Sensor {
    public:
        int status, trig, echo;

        virtual ~Sensor (void);
        virtual Sensor (int echo, int trig, int pulse);
        virtual void exec (void);
        virtual void exec2 (int a);
        virtual void toogleDelay (int delay);
}
#endif