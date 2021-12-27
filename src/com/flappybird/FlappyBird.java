package com.flappybird;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class FlappyBird implements Jogo {
    public double chao_movimento = 0; // variável de posição
    public double chaoVx = -70; //pode-se imaginar que possa ser a "velocidade"
    public double backgrond_movimento = 0;
    public double backgroundVx = -50;

    public Passaro passaro;
    public ArrayList<Cano> canos = new ArrayList<Cano>();
    public Random gerador = new Random();
    public Timer timer_cano;

    public FlappyBird() {
        passaro = new Passaro(35, (getLargura() - 112) / 2 + 24 / 2); // inicializa o passaro - calculo para começar na metade da tela do backgrounD
        timer_cano = new Timer(3, true, addCano());
        addCano().executa();
    }

    private Acao addCano() {
        return new Acao() {
            public void executa() {
                canos.add(new Cano(getLargura() + 10, gerador.nextInt(getLargura() - 112 - Cano.HOLESIZE), chaoVx));
            }
        };
    }

    public String getTitulo() {
        return "Jogo em Java";
    }

    // definir a largura e altura da minha tela do jogo
    public int getLargura() {
        return 384;
    }


    public int getAltura() {
        return 512;
    }

    @Override
    public void tique(Set<String> teclas, double dt) { // metodo que fica tudo que depende do tempo
        chao_movimento += dt * chaoVx; // chao irá variar a cada tique
        chao_movimento = chao_movimento % 308;

        backgrond_movimento += dt * backgroundVx;
        backgrond_movimento = backgrond_movimento % 288;

        passaro.atualiza(dt);

        timer_cano.tique(dt);

        for (Cano cano : canos) {
            cano.atualiza(dt);
        }
    }


    public void tecla(String tecla) {
        if (tecla.equals(" ")) {
            passaro.flap();
        }
    }


    public void desenhar(Tela t) {
        //fundo da imagem - background
        t.imagem("flappy.png", 0, 0, 288, 512, 0, backgrond_movimento, 0);
        t.imagem("flappy.png", 0, 0, 288, 512, 0, 280 + backgrond_movimento, 0);
        t.imagem("flappy.png", 0, 0, 288, 512, 0, 280 * 2 + backgrond_movimento, 0);

        for (Cano cano : canos) { //for que percorre todos os canos do meu ArrayList
            cano.desenhar(t);
        }

        //chão - ground
        t.imagem("flappy.png", 292, 0, 308, 112, 0, chao_movimento, getAltura() - 112);
        t.imagem("flappy.png", 292, 0, 308, 112, 0, 308 + chao_movimento, getAltura() - 112);
        t.imagem("flappy.png", 292, 0, 308, 112, 0, 308 * 2 + chao_movimento, getAltura() - 112);


        passaro.desenhar(t); // desenharo passaro
    }

    public static void main(String[] args) {
        roda();
    }

    private static void roda() { //pesquisar sobre métodos estáticos
        new Motor(new FlappyBird());
    }
}
