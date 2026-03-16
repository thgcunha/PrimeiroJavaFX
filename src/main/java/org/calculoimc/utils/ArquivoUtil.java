package org.calculoimc.utils;

import org.calculoimc.model.Pessoa;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {
    private static final String ARQUIVO = "dados_pessoas.txt";

    public static void salvar(List<Pessoa> pessoas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Pessoa p : pessoas) {
                bw.write(p.toString());
                bw.newLine();
            }
        }
    }

    public static List<Pessoa> carregar() throws IOException {
        List<Pessoa> lista = new ArrayList<>();
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return lista; // Retorna lista vazia se arquivo não existir
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                lista.add(Pessoa.fromString(linha));
            }
        }
        return lista;
    }
}
