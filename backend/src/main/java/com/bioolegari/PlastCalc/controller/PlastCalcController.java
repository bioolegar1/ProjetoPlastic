package com.bioolegari.PlastCalc.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calc")
public class PlastCalcController {

    @PostMapping("/calculate")
    public double calculate(@RequestBody CalcRequest request){
        double largura = request.getLargura();
        double altura = request.getAltura();
        double espessura = request.getEspessura();
        double quantidade = 1000.0;
        String material = request.getMaterial();
        int modelo = request.getModelo();
        int cores = request.getCores();

        double fator = 0.1;
        double materialValor =  getMaterialValue(material);
        double pesoMil = largura * altura * espessura * fator * materialValor;
        double pesoTotal = pesoMil * quantidade;

        if ((pesoTotal < 100 && (modelo == 1 || modelo == 0) && cores <= 4 && !material.equals("NYLON")) ||
        (pesoTotal < 300 && modelo == 0 && cores <= 6)){
            return 0;
        }else if ((pesoTotal > 100 && (modelo == 1 || modelo == 0) && cores <= 4  &&
        !material.equals("NYLON")) ||
            (pesoTotal > 300 && modelo == 0 && cores <= 6)) {
            return 100 / pesoMil;
        }
        return 0;
    }

    private double getMaterialValue(String material){
        return switch (material){
            case "PEBD" -> 0.92;
            case "PEAD" -> 0.95;
            case "PP" -> 0.90;
            case "BOPP TRANSP" -> 0.91;
            case "BOPP PEROLA" -> 0.70;
            case "NYLOM" -> 1.0;
            default -> 1.0;
        };
    }
}


class CalcRequest {
    private double largura;
    private double altura;
    private double espessura;
    private String material;
    private int modelo;
    private int cores;

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getEspessura() {
        return espessura;
    }

    public void setEspessura(double espessura) {
        this.espessura = espessura;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }
}

