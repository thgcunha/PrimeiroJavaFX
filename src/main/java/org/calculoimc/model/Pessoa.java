
package org.calculoimc.model;

public class Pessoa {
    private String nome;
    private double altura;
    private double peso;
    private double imc;
    private String classificacao;

    public Pessoa(String nome, double altura, double peso) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.imc = peso / (altura * altura);
        this.classificacao = calcularClassificacao(this.imc);
    }

    private String calcularClassificacao(double imc) {
        if (imc < 18.5) return "Abaixo do Peso";
        else if (imc < 24.9) return "Peso Normal";
        else if (imc < 29.9) return "Sobrepeso";
        else if (imc < 34.9) return "Obesidade Grau 1";
        else if (imc < 39.9) return "Obesidade Grau 2";
        else return "Obesidade Grau 3";
    }

    public String getNome() { return nome; }
    public double getAltura() { return altura; }
    public double getPeso() { return peso; }
    public double getImc() { return imc; }
    public String getClassificacao() { return classificacao; }

    @Override
    public String toString() {
        return nome + ";" + altura + ";" + peso + ";" + imc + ";" + classificacao;
    }

    public static Pessoa fromString(String linha) {
        String[] p = linha.split(";");
        return new Pessoa(p[0], Double.parseDouble(p[1]), Double.parseDouble(p[2]));
    }
}