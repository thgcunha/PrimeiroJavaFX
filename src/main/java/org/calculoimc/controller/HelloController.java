package org.calculoimc.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.calculoimc.model.Pessoa;
import org.calculoimc.utils.ArquivoUtil;

import java.io.*;

public class HelloController {

    @FXML private TextField txtNome;
    @FXML private TextField txtAltura;
    @FXML private TextField txtPeso;
    @FXML private Label lblResultado;
    @FXML private TableView<Pessoa> tableView;
    @FXML private TableColumn<Pessoa, String> colNome;
    @FXML private TableColumn<Pessoa, Double> colAltura;
    @FXML private TableColumn<Pessoa, Double> colPeso;
    @FXML private TableColumn<Pessoa, Double> colImc;
    @FXML private TableColumn<Pessoa, String> colClassificacao;

    private ObservableList<Pessoa> listaPessoas = FXCollections.observableArrayList();
    private static final String ARQUIVO = "pessoas.txt";

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colImc.setCellValueFactory(new PropertyValueFactory<>("imc"));
        colClassificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        tableView.setItems(listaPessoas);
    }

    @FXML
    public void calcularImc() {
        try {
            String nome = txtNome.getText().trim();
            double altura = Double.parseDouble(txtAltura.getText().trim());
            double peso = Double.parseDouble(txtPeso.getText().trim());

            if (nome.isEmpty()) {
                lblResultado.setText("Informe o nome!");
                return;
            }

            // ADICIONAR ESTA VALIDAÇÃO:
            if (altura <= 0 || peso <= 0) {
                lblResultado.setText("Altura e peso devem ser maiores que zero!");
                return;
            }

            Pessoa p = new Pessoa(nome, altura, peso);
            lblResultado.setText(String.format("IMC: %.2f - %s", p.getImc(), p.getClassificacao()));
            listaPessoas.add(p);

        } catch (NumberFormatException e) {
            lblResultado.setText("Altura e Peso devem ser números válidos!");
        }
    }

    @FXML
    public void salvar() {
        try {
            ArquivoUtil.salvar(listaPessoas);
            lblResultado.setText("Dados salvos com sucesso!");
        } catch (IOException e) {
            lblResultado.setText("Erro ao salvar: " + e.getMessage());
        }
    }

    @FXML
    public void carregar() {
        try {
            listaPessoas.clear();
            listaPessoas.addAll(ArquivoUtil.carregar());
            lblResultado.setText("Carregados: " + listaPessoas.size() + " pessoa(s)");
        } catch (IOException e) {
            lblResultado.setText("Erro ao carregar: " + e.getMessage());
        }
    }
}
