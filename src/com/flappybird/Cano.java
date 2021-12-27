package com.flappybird;

public class Cano {
    public double vxcano;
    public double x, y;
    public static int HOLESIZE = 120;

    public Cano(double x, double y, double vx) {
        this.vxcano = vx;
        this.x = x;
        this.y = y;
    }

    public void atualiza(double dt) {
        x += vxcano * dt;
    }

    public void desenhar(Tela t) {
        t.imagem("flappy.png", 604, 0, 52, 270, 0, x, y - 270); //cano de cima
        t.imagem("flappy.png", 660, 0, 52, 270, 0, x, y + Cano.HOLESIZE);
    }
}
