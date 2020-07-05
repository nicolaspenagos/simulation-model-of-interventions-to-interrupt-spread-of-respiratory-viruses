package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Data;
import model.SimulationData;
import view.Main;

public class DataController {

	// -------------------------------------
	// Constants
	// -------------------------------------
	public static final double GRAPH_WIDTH = 732.0;
	public static final double GRAPH_HEIGHT = 261.0;
	
	// -------------------------------------
	// FXML
	// -------------------------------------
	@FXML
	private MenuBar menuBar;

	@FXML
	private TableView<SimulationData> tableView;

	@FXML
	private TableColumn<SimulationData, Integer> id;

	@FXML
	private TableColumn<SimulationData, String> date;

	@FXML
	private Label numberOfPeople4;

	@FXML
	private Label numberOfPeople2;

	@FXML
	private Label numberOfPeople1;

	@FXML
	private Label numberOfPeople3;

	@FXML
	private Label days1;

	@FXML
	private Label days2;

	@FXML
	private Label days3;

	@FXML
	private Label days4;

	@FXML
	private Button goBack;

	@FXML
	private Label numberLabel;

	@FXML
	private Label dateLabel;
	
    @FXML
    private Label iPDay0Label;

    @FXML
    private Label hPDay0Label;

    @FXML
    private Label rPDay0Label;

    @FXML
    private Label iPDayNLabel;

    @FXML
    private Label hPDayNLabel;

    @FXML
    private Label rPDayNLabel;
	
    @FXML
    private Label timeLabel;
    
    @FXML
    private Label daysLabel;
    
    @FXML
    private Label iEffectivenessLabel;
   
    @FXML
    private Label infectedPeekLabel;
    
    @FXML
    private Label textLabel1;

    @FXML
    private Label textLabel2;

    @FXML
    private Label peekDayLabel;
    
    @FXML
    private Label restrictionLabel1;

    @FXML
    private Label restrictionLabel2;
    
    @FXML
    private Pane graphPane;
    

	private Main main;
	private ObservableList<SimulationData> dataList;
	private Data data;

	public void initialize() {

		String style = "-fx-font-weight: bold; -fx-background-color: SNOW;";
		menuBar.setStyle(style);
		data = new Data();

		id.setStyle("-fx-alignment: CENTER;");
		date.setStyle("-fx-alignment: CENTER;");

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		dataList = FXCollections.observableArrayList(data.getData());

		tableView.setItems(dataList);

		goBack.setGraphic(new ImageView(new Image("/images/goBack.png")));

		tableView.setRowFactory(tv -> {
			TableRow<SimulationData> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					SimulationData clickedRow = row.getItem();
					displayInfo(clickedRow);
				}
			});
			return row;
		});

		textLabel1.setVisible(false);
		textLabel2.setVisible(false);
	
	}

	private void displayInfo(SimulationData clickedRow) {

		int totalPeople = clickedRow.getHealthyPeopleAtDay0() + clickedRow.getRecoveredPeopleAtDay0()
				+ clickedRow.getInfectedPeopleAtDay0();

		numberLabel.setText("" + clickedRow.getId());
		dateLabel.setText(clickedRow.getDate());
		numberOfPeople4.setText("" + totalPeople);
		numberOfPeople3.setText("" + totalPeople * 3 / 4);
		numberOfPeople2.setText("" + totalPeople / 2);
		numberOfPeople1.setText("" + totalPeople / 4);
		
		iPDay0Label.setText(""+ clickedRow.getInfectedPeopleAtDay0());
		hPDay0Label.setText(""+ clickedRow.getHealthyPeopleAtDay0());
		rPDay0Label.setText(""+ clickedRow.getRecoveredPeopleAtDay0());
		
		iPDayNLabel.setText(""+ clickedRow.getInfectedPeopleAtDayN());
		hPDayNLabel.setText(""+ clickedRow.getHealthyPeopleAtDayN());
		rPDayNLabel.setText(""+ clickedRow.getRecoveredPeopleAtDayN());
		
		timeLabel.setText(clickedRow.getTime());
		daysLabel.setText("" + clickedRow.getDays());
		iEffectivenessLabel.setText(""+ (int)(clickedRow.getInterventionEffectiveness() * 100 ) + "%");
		
		peekDayLabel.setText(""+ clickedRow.getPeekDay());
		infectedPeekLabel.setText("" + clickedRow.getInfectedPeek());
		
		textLabel1.setVisible(true);
		textLabel2.setVisible(true);
		
		restrictionLabel1.setText(clickedRow.getRestriction());
		restrictionLabel2.setText(clickedRow.getRestriction_());

		toGraph(clickedRow.getGraph(), totalPeople, clickedRow.getDays());
		
		
	}

	public void setupMain(Main main) {

		this.main = main;

	}

	public void toGraph(ArrayList<Integer[]> graph, double totalPeople, double days) {

		graphPane.getChildren().clear();
	
		days1.setText("" + Math.round((days / 4)));
		days2.setText("" + Math.round(days / 2));
		days3.setText("" + Math.round(days * 3 / 4));
		days4.setText("" + Math.round(days));
		
		double barSize =  GRAPH_WIDTH / (days + 1);
	
		for (int i = 0; i < graph.size(); i++) {
			
	
			Integer[] arr = graph.get(i);
			double barHeight = 0, barHeight1 = 0, barHeight2 = 0;
			for (int j = 0; j < arr.length; j++) {
			
				if(j == 0) {
					
					barHeight = (double)arr[0] * GRAPH_HEIGHT / totalPeople;
					Rectangle rI = new Rectangle();
					rI.setWidth(barSize);
					rI.setHeight(barHeight);
					rI.setX(barSize * i );
					rI.setY(GRAPH_HEIGHT - barHeight);
					rI.setFill(Color.SALMON);
					graphPane.getChildren().add(rI);
					
				}else if(j == 1) {
					
					barHeight1 = (double)arr[1] * GRAPH_HEIGHT / totalPeople;
					Rectangle rH = new Rectangle();
					rH.setWidth(barSize);
					rH.setHeight(barHeight1);
					rH.setX(barSize * i );
					rH.setY(GRAPH_HEIGHT - barHeight - barHeight1);
					rH.setFill(Color.MEDIUMSEAGREEN);
					graphPane.getChildren().add(rH);
					
				}else {
					
					barHeight2 = (double)arr[2] * GRAPH_HEIGHT / totalPeople;
					Rectangle rH = new Rectangle();
					rH.setWidth(barSize);
					rH.setHeight(barHeight2);
					rH.setX(barSize * i );
					rH.setY(GRAPH_HEIGHT - barHeight - barHeight1 - barHeight2);
					rH.setFill(Color.STEELBLUE);
					graphPane.getChildren().add(rH);
					
				}
				
			}
			
		}

	}

	@FXML
	void goToMainView(ActionEvent event) {
		main.changeScene("graphicalUserInterface.fxml");
	}

}
