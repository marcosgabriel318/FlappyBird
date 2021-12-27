package com.flappybird;

public class Passaro {

    public double x, y;
    public double vy = 0; //velocidade da altura

    public static double G = 800; //funciona como se fosse a gravidade
    public static double FLAP = -300;

    public Passaro(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void atualiza(double dt) {
        vy += G * dt;
        y += vy * dt;
    }

    public void flap() {
        vy = FLAP;
    }

    public void desenhar(Tela t) {
        t.imagem("flappy.png", 528, 128, 34, 24, Math.atan(vy / 150), x, y);
    }
}
