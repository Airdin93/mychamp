/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package mycham.GUI.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mycham.BE.Team;
import mycham.GUI.Model.TeamModel;

/**
 *
 * @author Emil
 */

public class MainViewController implements Initializable {
    
    public boolean eventStarted = false;
    
    private TeamModel teamModel;
    
    private Team team;
    
    @FXML
    private TextField textFieldAddTeam;
    
    @FXML
    private TableColumn<Team ,String> listTeamView;
    
    @FXML
    private Button finalsButton;
    
    @FXML
    private Label publicMessageLabel;
    
    @FXML
    private Button groupStageButton;
    @FXML
    private TableView<Team> tableTeam;
    
    public MainViewController()
    {
        teamModel = TeamModel.getInstance();
    }     
    
    @FXML
    private void groupStage(ActionEvent event) throws IOException
    {
        
        Stage primStage = (Stage)groupStageButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mycham/GUI/view/MatchSchedule.fxml"));
        Parent root = loader.load();
  
        Stage stageMainView = new Stage();
        stageMainView.setScene(new Scene(root));
        
        stageMainView.setTitle("Group Stage");
        
        stageMainView.initModality(Modality.WINDOW_MODAL);
        stageMainView.initOwner(primStage);
        
        stageMainView.show();
    }
    
    @FXML
    private void finals(ActionEvent event) throws IOException
    {
        Stage primStage = (Stage)finalsButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mycham/GUI/view/Finals.fxml"));
        Parent root = loader.load();
  
        Stage stageFinals = new Stage();
        stageFinals.setScene(new Scene(root));
        
        stageFinals.setTitle("Finals");
        
        stageFinals.initModality(Modality.WINDOW_MODAL);
        stageFinals.initOwner(primStage);
        
        stageFinals.show();
    }
    
    
    @FXML
    private void addTeam(ActionEvent event) 
    {
        addTeamMethod();
    }
    
    public void addTeamMethod ()
    {
        if (!textFieldAddTeam.getText().isEmpty() && eventStarted == false)
        {
//            teamModel.createTeam(textFieldAddTeam.getText());
            teamModel.createTeam(textFieldAddTeam.getText(), 0, 0, 0);
            textFieldAddTeam.clear();
        }
    }
    
    @FXML
    public void handleEnterPressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER) 
        {
            addTeamMethod();
        }
    }
    
    
    
    @FXML
    private void editTeam(ActionEvent event) 
    {
        final int selectedItem = tableTeam.getSelectionModel().getSelectedIndex();
        if (selectedItem != -1)
        {
            if(textFieldAddTeam.getText().isEmpty())
            {
                textFieldAddTeam.setText(tableTeam.getItems().get(selectedItem).toString());
            }
            else
            {
                teamModel.getTeam().get(selectedItem).setName(textFieldAddTeam.getText());
            }
            tableTeam.getColumns().get(0).setVisible(false);
            tableTeam.getColumns().get(0).setVisible(true);
        }
    }
    
    @FXML
    private void deleteTeam(ActionEvent event)
    {
        final int selectedItem = tableTeam.getSelectionModel().getSelectedIndex();
        if (selectedItem != -1 && eventStarted == false)
        {
            tableTeam.getItems().remove(selectedItem);
        }
        else if(selectedItem != -1 && eventStarted == true)
        {
            textFieldAddTeam.clear();
        }
    }
    
    
    //DELETE THIS -v
    @FXML
    private void testButton2(ActionEvent event) 
    {
        for(int i = 0; i < teamModel.getTeam().size(); i++) 
        {   
            System.out.println(teamModel.getTeam().get(i));
        }
    }
    
    @FXML
    private void shuffleButton(ActionEvent event) 
    {
        Collections.shuffle(teamModel.getTeam());
    }
    
    public void initialize(URL url, ResourceBundle rb) 
    {
        tableTeam.setItems(teamModel.getTeam());
        listTeamView.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
    }    
    
    @FXML
    private void startTour(ActionEvent event) 
    {
        if(teamModel.getTeam().size() >= 12 && teamModel.getTeam().size() <= 16)
        {
            eventStarted = true;
            publicMessageLabel.setText("Tour started!");
        }
        else
        {
            publicMessageLabel.setText("Team is not correct size!");
        }
    }

    @FXML
    private void resetTour(ActionEvent event) 
    {
        eventStarted = false;
        tableTeam.getItems().removeAll(teamModel.getTeam());
        publicMessageLabel.setText("New event initiated!");
    }
    
    /*
    NOTE TO EVERYONE!
    Hardcode below, to be removed!
    If you are a teacher, don't look at it!
    Unless you prefer to add 12 teams automatically
    */

    @FXML
    private void addDummy(ActionEvent event) 
    {
        if(eventStarted == false)
        {
            teamModel.getTeam().add(new Team(1, 2, 3, "Alpha"));
//            teamModel.getTeam().add(new Team(1, 2, 2, "kasdka"));
//            teamModel.getTeam().add(new Team("Bravo"));
//            teamModel.getTeam().add(new Team("Charlie"));
//            teamModel.getTeam().add(new Team("Delta"));
//            teamModel.getTeam().add(new Team("Echo"));
//            teamModel.getTeam().add(new Team("Foxtrot"));
//            teamModel.getTeam().add(new Team("Golf"));
//            teamModel.getTeam().add(new Team("Hotel"));
//            teamModel.getTeam().add(new Team("India"));
//            teamModel.getTeam().add(new Team("Juliett"));
//            teamModel.getTeam().add(new Team("Kilo"));
//            teamModel.getTeam().add(new Team("Magic Mike"));
//            teamModel.getTeam().add(new Team(0id, 0goals, 0point, name))
        }       
    }
}