
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application  {
	
	
    private Scene dashboardScene;
    private ObservableList<ObservableList> data;
    private TableView tableview;
    private Connection c;
    private Alert alert = new Alert(AlertType.NONE);


	
	@Override
	public void start(Stage stage) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");  
    	 c=DriverManager.getConnection(  
    			"jdbc:mysql://localhost:3306/Lab77","root",""); 
    	 
			Statement stmt=c.createStatement(); 

		VBox vbox = new VBox();
		
		Label l = new Label("Dareen Obeid 201904878 ");
		l.setTextFill(Color.web("#0076a3"));
		l.setFont(Font.font("SanSerif", 20));
		vbox.setSpacing(10);
		
        HBox sideBar = new HBox();
        BorderPane dashboardLayout = new BorderPane();
        dashboardScene = new Scene(dashboardLayout, 950, 800);
        
        
        dashboardLayout.setTop(vbox);
        BorderPane.setMargin(sideBar, new Insets(30, 30, 0, 10));
		vbox.getChildren().addAll(l,sideBar);

		
//        Label l = new Label("database");
//        l.setTextFill(Color.web("#0076a3"));
//        l.setFont(Font.font("SanSerif", 20));
//        dashboardLayout.setTop(l);
		
        Button advisorBtn = new Button("advisor");
        advisorBtn.setFont(Font.font("SanSerif", 15));
        advisorBtn.setMaxWidth(120);
        
        Button classroomBtn = new Button("classroom");
        classroomBtn.setFont(Font.font("SanSerif", 15));
        classroomBtn.setMaxWidth(120);
		
        Button courseBtn = new Button("course");
        courseBtn.setFont(Font.font("SanSerif", 15));
        courseBtn.setMaxWidth(120);
        
        Button departmentBtn = new Button("department");
        departmentBtn.setFont(Font.font("SanSerif", 15));
        departmentBtn.setMaxWidth(120);
        
        Button instructorBtn = new Button("instructor");
        instructorBtn.setFont(Font.font("SanSerif", 15));
        instructorBtn.setMaxWidth(120);
        
        Button prereqBtn = new Button("prereq");	
        prereqBtn.setFont(Font.font("SanSerif", 15));
        prereqBtn.setMaxWidth(120);
        
        Button sectionBtn = new Button("section");        
        sectionBtn.setFont(Font.font("SanSerif", 15));
        sectionBtn.setMaxWidth(120);
        
        Button studentBtn = new Button("student");	
        studentBtn.setFont(Font.font("SanSerif", 15));
        studentBtn.setMaxWidth(120);
        
        Button takesBtn = new Button("takes");
        takesBtn.setFont(Font.font("SanSerif", 15));
        takesBtn.setMaxWidth(120);
        
        Button teachesBtn = new Button("teaches");	
        teachesBtn.setFont(Font.font("SanSerif", 15));
        teachesBtn.setMaxWidth(120);
        
        Button time_slotBtn = new Button("time_slot");
        time_slotBtn.setFont(Font.font("SanSerif", 15));
        time_slotBtn.setMaxWidth(120);
        
        
        
        BorderPane advisorPage = new BorderPane();
        Scene advisorScene = new Scene(advisorPage, 870, 600);
        
        
        
        advisorBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("advisor");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }  
            
  
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label sID = new Label("s_ID");
          grid.add(sID, 0, 0);
          ComboBox s_ID = new ComboBox();
          grid.add(s_ID, 1, 0);
          String SQL = "SELECT ID from student";
          ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(SQL);
	          while(rs.next()) {
	        	  s_ID.getItems().addAll(rs.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


          
          Label iID = new Label("i_ID");
          grid.add(iID, 0, 1);
          ComboBox i_ID = new ComboBox();
          grid.add(i_ID, 1, 1);
          String SQL2 = "SELECT ID from instructor";
          try {
			ResultSet rs2 = c.createStatement().executeQuery(SQL2);
	          while(rs2.next()) {
	        	  i_ID.getItems().addAll(rs2.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
          Button search = new Button("search");
          
          insert.disableProperty().bind(s_ID.valueProperty().isNull()
        		  					.or(i_ID.valueProperty().isNull()));

        		       
          

          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 1, 4);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = (String)s_ID.getSelectionModel().getSelectedItem();
              String a2 = (String)i_ID.getSelectionModel().getSelectedItem();
              
              String query = "insert into advisor  values ('"+ 
            		  a1+ "', '"+
    		          a2 +"')";    		
              System.out.println(query);
              
              
              String s1 = "";
              ResultSet rss;
			try {
				rss = stmt.executeQuery("SELECT s_ID FROM advisor");
				while (rss.next()) {                            
					s1 += rss.getString(1)+ " ";  
					
					System.out.println(s1);
					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(a1)) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This s_ID "+ a1 +" is in the databse");
      				alert.show();	
              }   
              else { 
	  			try {
						stmt.executeUpdate(query);
						resultTxt.setText("advisor with s_ID " + a1 + " was added");
	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}  
              }
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("advisor");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          
		  search.setOnAction(z->{

              
			  String query="select * from advisor ";
			  
			  
			  if(s_ID.getValue() != null || i_ID.getValue() != null) {
				  query+= "where ";
				  
				  if (s_ID.getValue() != null)
					  query+= "s_ID = " + s_ID.getValue()+ " AND";
				  if (i_ID.getValue() != null)
					  query+= " i_ID = " + i_ID.getValue();

				  
			  }

			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs2=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs2.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs2.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }

		  });

          dashboardLayout.setCenter(tableview);
     
        });
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
        classroomBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("classroom");
         
                //Main Scene
         
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label cbuilding = new Label("building");
          grid.add(cbuilding, 0, 0);
          TextField building  = new TextField(); 
          grid.add(building, 1, 0);

          
          
          Label croom_number = new Label("room_number");
          grid.add(croom_number, 0, 1);
          TextField room_number = new TextField();
          grid.add(room_number, 1, 1);

          
          Label ccapacity = new Label("capacity");
          grid.add(ccapacity, 0, 2);
          TextField capacity = new TextField();
          grid.add(capacity, 1, 2);
          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
		  Button search=new Button("search");

          
          
          insert.disableProperty().bind(
        		    Bindings.isEmpty(building.textProperty())
        		    .or(Bindings.isEmpty(room_number.textProperty()))
        		    .or(Bindings.isEmpty(capacity.textProperty()))
        		);

          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 1, 4);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
			

          
          
          //insertClassroom
          insert.setOnAction(i -> {
          	
              String a1 = building.getText();
              String a2 = room_number.getText();
              String a3 =  capacity.getText();
              
              String query = "insert into classroom  values ('"+ 
            		  a1+ "', '"+
    		          a2 +"', '"+ 
    		          a3 +"')";    		
              System.out.println(query);
              
              String s1 = "";
              ResultSet rs;
			try {
				rs = stmt.executeQuery("SELECT building,room_number FROM classroom");
				while (rs.next()) {                            
					s1 += rs.getString(1)+ rs.getString(2)+ " ";  
					
					System.out.println(s1);
					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(building.getText()+room_number.getText())) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This building "+ building.getText() +" and room_number "+ room_number.getText() +" are in the databse");
      				alert.show();	
              }
              
              
              else if(!isNumber(capacity.getText())) {
  				alert.setAlertType(AlertType.WARNING);
  				alert.setContentText("capacity must be number");
  				alert.show();			
                }
  			else {
	  			try {
						stmt.executeUpdate(query);
						resultTxt.setText("classroom with building " + building.getText() +" and room_number "+room_number.getText() +" was added");
	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
  			}
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("classroom");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
		  search.setOnAction(z->{

			  String query="select * from classroom ";
			  
			  
			  if(!building.getText().isEmpty() || !room_number.getText().isEmpty() || !capacity.getText().isEmpty()) {
				  query+= "where ";
				  
				  if (!building.getText().isEmpty())
					  query+= "building = '" + building.getText()+ "' AND";
				  if (!room_number.getText().isEmpty())
					  query+= " room_number = " + room_number.getText()+ " AND";
				  if (!capacity.getText().isEmpty())
					  query+= " capacity = " + capacity.getText();
				  
			  }

			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
//	            try {
//	                //TableView
//	                tableview = new TableView();
//	                buildQuery("query");
//	         
//	                //Main Scene
//	         
////	                dashboardLayout.setBottom(tableview); 
//	                dashboardLayout.setCenter(tableview);
//	                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));
//
//	                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//	                    @Override
//	                    public void handle(WindowEvent event) {
//	                        Platform.exit();
//	                        System.exit(0);
//	                    }
//	                });
//	            } catch (Exception el) {
//	                System.out.println(el);
//	            }
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs2=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs2.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs2.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }
			  
			  
			 
			  
			  
		  });
          
          

          dashboardLayout.setCenter(tableview);
            
        
        });
       
        
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        courseBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("course");
         
                //Main Scene
         
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label cID = new Label("course_id ");
          grid.add(cID, 0, 0);
          TextField course_id  = new TextField(); 
          grid.add(course_id, 1, 0);

          
          
          Label ctitle = new Label("title");
          grid.add(ctitle, 0, 1);
          TextField title = new TextField();
          grid.add(title, 1, 1);
          
          Label deptname = new Label("dept_name");
          grid.add(deptname, 0, 2);
          ComboBox dept_name = new ComboBox();
          grid.add(dept_name, 1, 2);
          String SQL = "SELECT dept_name from department";
          ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(SQL);
	          while(rs.next()) {
	        	  dept_name.getItems().addAll(rs.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

          
          Label ccredits = new Label("credits");
          grid.add(ccredits, 0, 3);
          TextField credits = new TextField();
          grid.add(credits, 1, 3);
          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
		  Button search=new Button("search");

      
 
          insert.disableProperty().bind(
      		    Bindings.isEmpty(course_id.textProperty())
      		    .or(Bindings.isEmpty(title.textProperty()))
      		    .or(Bindings.isEmpty(credits.textProperty()))
      		    .or(dept_name.valueProperty().isNull())
      		);

          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 1, 4);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = course_id.getText();
              String a2 = title.getText();
              String a3 = (String)dept_name.getSelectionModel().getSelectedItem();
              String a4 = credits.getText();

              
              String query = "insert into course  values ('"+ 
            		  a1+ "', '"+
    		          a2 +"', '"+ 
    		          a3 +"', '"+
    		          a4 +"')";    		
              System.out.println(query);
              
              
              String s1 ="";
              ResultSet rs2;
			try {
				rs2 = stmt.executeQuery("SELECT course_id FROM course");
				while (rs2.next()) {                            
					s1 += rs2.getString(1)+" "; 
					 System.out.println(s1);

					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(course_id.getText())) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This building "+ course_id.getText() +" is in the databse");
      				alert.show();	
              }
              
              
              
              else if(!isNumber(credits.getText())) {
            	  	alert.setAlertType(AlertType.WARNING);
                    alert.setContentText("credits must be number");
                    alert.show();			
                    }
              
              else {
  			
            	  try {
					stmt.executeUpdate(query);
					resultTxt.setText("course with dept_name " + course_id.getText() + " was added");

            	  } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}   
      			}
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("course");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          
		  search.setOnAction(z->{
			           
			  String query="select * from course ";
			  
			  
			  if(!course_id.getText().isEmpty() || !title.getText().isEmpty() ||dept_name.getValue() != null || !credits.getText().isEmpty()) {
				  query+= "where ";
				  
				  
				  if (!course_id.getText().isEmpty())
					  query+= "course_id = '" + course_id.getText()+ "' AND";
				  if (!title.getText().isEmpty())
					  query+= " title = '" + title.getText()+ "' AND";
				  if (dept_name.getValue() != null)
					  query+= " dept_name = '" + dept_name.getValue()+ "' AND";
				  if (!credits.getText().isEmpty())
					  query+= " credits = " + credits.getText();
				  
			  }

			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs2=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs2.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs2.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }

		  });

          dashboardLayout.setCenter(tableview);
            
        
        });
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        departmentBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("department");
         
                //Main Scene
         
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
            
            
//            VBox vbox = new VBox();
//            
//            Label dname = new Label("dept_name ");
//            TextField dept_name  = new TextField();    
//            HBox hbox1= new HBox(dname, dept_name );
//            vbox.getChildren().add(hbox1);
//            
//            Label dbuilding = new Label("building");
//            TextField building = new TextField();
//            HBox hbox2 = new HBox(dbuilding, building );
//            vbox.getChildren().add(hbox2);  
//            
//            Label dbudget = new Label("budget");
//            TextField budget = new TextField();
//            HBox hbox3 = new HBox(dbudget,budget);
//            vbox.getChildren().add(hbox3);
//            
//            Button button = new Button("Insert");
//            HBox hbox4 = new HBox(button);
//
//            button.setAlignment(Pos.BOTTOM_CENTER);
//            vbox.getChildren().add(hbox4);
//            
//            dashboardLayout.setLeft(vbox);
//            BorderPane.setMargin(vbox, new Insets(30, 30, 300, 30));
//            vbox.setSpacing(20);

            
            
            
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label dname = new Label("dept_name ");
          grid.add(dname, 0, 0);
          TextField dept_name  = new TextField(); 
          grid.add(dept_name, 1, 0);

          
          
          Label dbuilding = new Label("building");
          grid.add(dbuilding, 0, 1);
          TextField building = new TextField();
          grid.add(building, 1, 1);

          
          Label dbudget = new Label("budget");
          grid.add(dbudget, 0, 2);
          TextField budget = new TextField();
          grid.add(budget, 1, 2);
          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
          Button search = new Button("search");
          
          
          insert.disableProperty().bind(
      		    Bindings.isEmpty(dept_name.textProperty())
      		    .or(Bindings.isEmpty(building.textProperty()))
      		    .or(Bindings.isEmpty(budget.textProperty()))
      		);

          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 1, 4);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = dept_name.getText();
              String a2 = building.getText();
              String a3 =  budget.getText();
              
              String query = "insert into department  values ('"+ 
            		  a1+ "', '"+
    		          a2 +"', '"+ 
    		          a3 +"')";    		
              System.out.println(query);
              
              
              
              String s1 = "";
              ResultSet rs;
			try {
				rs = stmt.executeQuery("SELECT dept_name FROM department");
				while (rs.next()) {                            
					s1 += rs.getString(1)+ " ";  
//					System.out.println(s1);
					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(dept_name.getText())) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This dept_name "+ dept_name.getText() +" is in the databse");
      				alert.show();	
              }
              
              else if(!isNumber(budget.getText())) {
            	  alert.setAlertType(AlertType.WARNING);
            	  alert.setContentText("budget must be number");
            	  alert.show();			
                  }
          
              else {
	  			try {
						stmt.executeUpdate(query);
						resultTxt.setText("department with dept_name " + dept_name.getText() + " was added");
	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
              }
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("department");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          
		  search.setOnAction(z->{

			  String query="select * from department ";
			  
			  
			  if(!dept_name.getText().isEmpty() || !building.getText().isEmpty() || !budget.getText().isEmpty()) {
				  query+= "where ";
				  
				  
				  if (!dept_name.getText().isEmpty())
					  query+= "dept_name = '" + dept_name.getText()+ "' AND";
				  if (!building.getText().isEmpty())
					  query+= " building = '" + building.getText()+ "' AND";

				  if (!budget.getText().isEmpty())
					  query+= " budget = " + budget.getText();
				  
			  }

			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs2=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs2.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs2.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }  
			  
		  });

          dashboardLayout.setCenter(tableview);
            
        });
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        instructorBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("instructor");
         
                //Main Scene
         
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label iID = new Label("ID");
          grid.add(iID, 0, 0);
          TextField ID  = new TextField(); 
          grid.add(ID, 1, 0);

          
          
          Label cname = new Label("name");
          grid.add(cname, 0, 1);
          TextField name = new TextField();
          grid.add(name, 1, 1);
          
          Label deptname = new Label("dept_name");
          grid.add(deptname, 0, 2);
          ComboBox dept_name = new ComboBox();
          grid.add(dept_name, 1, 2);
          String SQL = "SELECT dept_name from department";
          ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(SQL);
	          while(rs.next()) {
	        	  dept_name.getItems().addAll(rs.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

          
          Label isalary = new Label("salary");
          grid.add(isalary, 0, 3);
          TextField salary = new TextField();
          grid.add(salary, 1, 3);
          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
          Button search = new Button("search");
          
          insert.disableProperty().bind(
        		    Bindings.isEmpty(ID.textProperty())
        		    .or(Bindings.isEmpty(name.textProperty()))
        		    .or(Bindings.isEmpty(salary.textProperty()))
        		    .or(dept_name.valueProperty().isNull())
        		);
          

          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 1, 4);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = ID.getText();
              String a2 = name.getText();
              String a3 = (String)dept_name.getSelectionModel().getSelectedItem();
              String a4 = salary.getText();

              
              String query = "insert into instructor  values ('"+ 
            		  a1+ "', '"+
    		          a2 +"', '"+ 
    		          a3 +"', '"+
    		          a4 +"')";    		
              System.out.println(query);
              
              
              
              
              String s1 = "";
              ResultSet rs2;
			try {
				rs2 = stmt.executeQuery("SELECT ID FROM instructor");
				while (rs2.next()) {                            
					s1 += rs2.getString(1)+ " ";  
//					System.out.println(s1);
					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(ID.getText())) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This ID "+ ID.getText() +" is in the databse");
      				alert.show();	
              }
              
              
              else if(!isNumber(salary.getText())) {
            	  alert.setAlertType(AlertType.WARNING);
            	  alert.setContentText("salary must be number");
            	  alert.show();			
                  }
          
              else {
  			
	  			try {
						stmt.executeUpdate(query);
						resultTxt.setText("course with ID " + ID.getText() + " was added");
	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}   
              }
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("instructor");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          search.setOnAction(z->{
              String a1 = ID.getText();
              String a2 = name.getText();
              String a3 = (String)dept_name.getSelectionModel().getSelectedItem();
              String a4 = salary.getText();

			  String query="select * from instructor ";
			  
			  
			  if(!ID.getText().isEmpty() || !name.getText().isEmpty() ||dept_name.getValue() != null || !salary.getText().isEmpty()) {
				  query+= "where ";
				  
				  
				  if (!ID.getText().isEmpty())
					  query+= "ID = " + ID.getText()+ " AND";
				  if (!name.getText().isEmpty())
					  query+= " name = '" + name.getText()+ "' AND";
				  if (dept_name.getValue() != null)
					  query+= " dept_name = '" + dept_name.getValue()+ "' AND";
				  if (!salary.getText().isEmpty())
					  query+= " salary = " + salary.getText();
				  
			  }
			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs2=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs2.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs2.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }
  
		  });

          dashboardLayout.setCenter(tableview);
            
        
        });
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    
        prereqBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("prereq");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }  
            
  
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label courseid = new Label("course_id");
          grid.add(courseid, 0, 0);
          ComboBox course_id = new ComboBox();
          grid.add(course_id, 1, 0);
          String SQL = "SELECT course_id from course";
          ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(SQL);
	          while(rs.next()) {
	        	  course_id.getItems().addAll(rs.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        Label prereqid = new Label("prereq_id");
        grid.add(prereqid, 0, 1);
        ComboBox prereq_id = new ComboBox();
        grid.add(prereq_id, 1, 1);
        String SQL2 = "SELECT course_id from course";
        ResultSet rs2;
		try {
			rs2 = c.createStatement().executeQuery(SQL2);
	          while(rs2.next()) {
	        	  prereq_id.getItems().addAll(rs2.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
    	  Button search=new Button("search");
          
          insert.disableProperty().bind(course_id.valueProperty().isNull()
					.or(prereq_id.valueProperty().isNull()));
          


          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 1, 4);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = (String)course_id.getSelectionModel().getSelectedItem();
              String a2 = (String)prereq_id.getSelectionModel().getSelectedItem();
              
              String query = "insert into prereq  values ('"+ 
            		  a1+ "', '"+
    		          a2 +"')";    		
              System.out.println(query);
  			
  			try {
					stmt.executeUpdate(query);
					resultTxt.setText("prereq with course_id " + a1 + " was added");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("prereq");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          search.setOnAction(z->{

              
			  String query="select * from prereq ";
			  
			  
			  if(course_id.getValue() != null || prereq_id.getValue() != null) {
				  query+= "where ";
				  
				  if (course_id.getValue() != null)
					  query+= "course_id = '" + course_id.getValue()+ "' AND";
				  if (prereq_id.getValue() != null)
					  query+= " prereq_id = '" + prereq_id.getValue()+ "'";

				  
			  }

			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs3=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs3.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs3.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs3.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs3.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }
			  
		  });

          

          dashboardLayout.setCenter(tableview);
     
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        sectionBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("section");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }  
            
  
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label courseid = new Label("course_id");
          grid.add(courseid, 0, 0);
          ComboBox course_id = new ComboBox();
          grid.add(course_id, 1, 0);
          String SQL = "SELECT course_id from course";
          ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(SQL);
	          while(rs.next()) {
	        	  course_id.getItems().addAll(rs.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
        Label secid = new Label("sec_id");
        grid.add(secid, 0, 1);
        TextField sec_id = new TextField();
        grid.add(sec_id, 1, 1);

        Label sem = new Label("semester");
        grid.add(sem, 0, 2);
        ComboBox semester = new ComboBox();
        semester.getItems().addAll(
        	    "Fall",
        	    "Spring",
        	    "Summer"
        	);
        grid.add(semester, 1, 2);
        
        Label y = new Label("year");
        grid.add(y, 0, 3);
        TextField year = new TextField();
        grid.add(year, 1, 3);
        
        Label b = new Label("building + roomN");
        grid.add(b, 0, 4);
        ComboBox building = new ComboBox();
        grid.add(building, 1, 4);
        String SQL4 = "SELECT building,room_number from classroom";
        ResultSet rs4;
		try {
			rs4 = c.createStatement().executeQuery(SQL4);
	          while(rs4.next()) {
	        	  building.getItems().addAll(rs4.getString(1) +" "+ rs4.getString(2));

	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        Label time_slotid = new Label("time_slot_id");
        grid.add(time_slotid, 0, 5);
        ComboBox time_slot_id = new ComboBox();
        grid.add(time_slot_id, 1, 5);
        String SQL3 = "SELECT DISTINCT time_slot_id from time_slot";
        ResultSet rs3;
		try {
			rs3 = c.createStatement().executeQuery(SQL3);
	          while(rs3.next()) {
	        	  time_slot_id.getItems().addAll(rs3.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        


          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
          Button search = new Button("search");


          
          insert.disableProperty().bind(
      		    Bindings.isEmpty(sec_id.textProperty())
      		    .or(Bindings.isEmpty(year.textProperty()))
        		    .or(course_id.valueProperty().isNull())
        		    .or(building.valueProperty().isNull())
        		    .or(time_slot_id.valueProperty().isNull())
        		    .or(semester.valueProperty().isNull())

      		);


          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 1, 6);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = (String)course_id.getSelectionModel().getSelectedItem();
              String a2 =  sec_id.getText();
              String a3 = (String)semester.getSelectionModel().getSelectedItem();
              String a4 =  year.getText();
              String a = (String)building.getSelectionModel().getSelectedItem();
              String[] x =  a.split(" ");
              String a5 = x[0];
              String a6 = x[1];
              String a7 = (String)time_slot_id.getSelectionModel().getSelectedItem();



              String query = "insert into section  values ('"+ 
            		  a1+ "', '"+
            		  a2+ "', '"+
            		  a3+ "', '"+
            		  a4+ "', '"+
            		  a5+ "', '"+
            		  a6+ "', '"+
    		          a7+"')";    		
              System.out.println(query);
              
              
              String s1 = "";
              ResultSet rs2;
			try {
				rs2 = stmt.executeQuery("SELECT course_id,sec_id,semester,year FROM section");
				while (rs2.next()) {                            
					s1 += rs2.getString(1)+ rs2.getString(2)+ rs2.getString(3)+ rs2.getString(4) +" ";  
					
					System.out.println(s1);
					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(course_id.getValue()+sec_id.getText()+semester.getValue()+year.getText())) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This course_id "+ course_id.getValue()+" and sec_id "+ sec_id.getText() 
      										+" and semester "+ semester.getValue() +" and year "+ year.getText() 
      										+" are in the databse");
      				alert.show();	
              }
              
              
              else if(!isNumber(year.getText())) {
    				alert.setAlertType(AlertType.WARNING);
    				alert.setContentText("year must be number");
    				alert.show();			
                    }
      			else {

	  			try {
						stmt.executeUpdate(query);
						resultTxt.setText("section with course_id " + a1 + " was added");
	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}   
      			}
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("section");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          
          search.setOnAction(z->{
	           
			  String query="select * from section ";
      
//              String a = (String)building.getSelectionModel().getSelectedItem();
//              String[] x =  a.split(" ");
//              String a5 = x[0];
//              String a6 = x[1];
			  String a = null;
			  
			  if(course_id.getValue() != null || !sec_id.getText().isEmpty() || semester.getValue() != null || !year.getText().isEmpty() ||
					  building.getValue() != null || time_slot_id.getValue() != null ) {
				  query+= "where ";

				  if (course_id.getValue() != null)
					  query+= " course_id = '" + course_id.getValue()+ "' AND";
				  if (!sec_id.getText().isEmpty())
					  query+= " sec_id = " + sec_id.getText()+ " AND";
				  if (semester.getValue() != null)
					  query+= " semester = '" + semester.getValue()+ "' AND";
				  if (!year.getText().isEmpty())
					  query+= " year = " + year.getText()+ " AND";
				  if (building.getValue() != null)
		               a = (String)building.getSelectionModel().getSelectedItem();
		              String[] x =  a.split(" ");
		              String a5 = x[0];
		              String a6 = x[1];

					  query+= " building = '" + a5 + "' AND room_number= '"+ a6+ "' AND";
				  if (time_slot_id.getValue() != null)
					  query+= " time_slot_id = '" + time_slot_id.getValue()+ "'";
				  
			  }

			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs2=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs2.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs2.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }
 
		  });

          dashboardLayout.setCenter(tableview);
     
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
      
        studentBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("student");
         
                //Main Scene
         
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label sID = new Label("ID");
          grid.add(sID, 0, 0);
          TextField ID  = new TextField(); 
          grid.add(ID, 1, 0);

          
          
          Label sname = new Label("name");
          grid.add(sname, 0, 1);
          TextField name = new TextField();
          grid.add(name, 1, 1);
          
          Label deptname = new Label("dept_name");
          grid.add(deptname, 0, 2);
          ComboBox dept_name = new ComboBox();
          grid.add(dept_name, 1, 2);
          String SQL = "SELECT dept_name from department";
          ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(SQL);
	          while(rs.next()) {
	        	  dept_name.getItems().addAll(rs.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

          
          Label stot_cred = new Label("tot_cred");
          grid.add(stot_cred, 0, 3);
          TextField tot_cred = new TextField();
          grid.add(tot_cred, 1, 3);
          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
          Button search = new Button("search");
          
          
          insert.disableProperty().bind(
        		    Bindings.isEmpty(ID.textProperty())
        		    .or(Bindings.isEmpty(name.textProperty()))
        		    .or(Bindings.isEmpty(tot_cred.textProperty()))
          		    .or(dept_name.valueProperty().isNull())
        		);

          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);

          grid.add(hbox, 1, 4);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = ID.getText();
              String a2 = name.getText();
              String a3 = (String)dept_name.getSelectionModel().getSelectedItem();
              String a4 =  tot_cred.getText();
              
              String query = "insert into student  values ('"+ 
            		  a1+ "', '"+
    		          a2 +"', '"+ 
    		          a3 +"', '"+
    		          a4+"')";    		
              System.out.println(query);
              
              String s1 = "";
              ResultSet rs2;
			try {
				rs2 = stmt.executeQuery("SELECT ID FROM student");
				while (rs2.next()) {                            
					s1 += rs2.getString(1)+ " ";  
//					System.out.println(s1);
					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(ID.getText())) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This ID "+ ID.getText() +" is in the databse");
      				alert.show();	
              }
              
              
              else if(!isNumber(tot_cred.getText())) {
				alert.setAlertType(AlertType.WARNING);
				alert.setContentText("tot_cred must be number");
				alert.show();			
                }
  			else {
  			
	  			try {
						stmt.executeUpdate(query);
						resultTxt.setText("student with building " + ID.getText() +" and dept_name "+(String)dept_name.getSelectionModel().getSelectedItem() +" was added");
	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}   
  			}
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("student");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          
          search.setOnAction(z->{
	           
			  String query="select * from student ";
			  
			  
			  if(!ID.getText().isEmpty() || !name.getText().isEmpty() ||dept_name.getValue() != null || !tot_cred.getText().isEmpty()) {
				  query+= "where ";
				  
				  
				  if (!ID.getText().isEmpty())
					  query+= "ID = " + ID.getText()+ " AND";
				  if (!name.getText().isEmpty())
					  query+= " name = '" + name.getText()+ "' AND";
				  if (dept_name.getValue() != null)
					  query+= " dept_name = '" + dept_name.getValue()+ "' AND";
				  if (!tot_cred.getText().isEmpty())
					  query+= " tot_cred = " + tot_cred.getText();
				  
			  }

			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs2=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs2.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs2.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }
			  
			  
			 
			  
			  
		  });

          dashboardLayout.setCenter(tableview);
            
        
        });
             
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        takesBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("takes");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
//                System.out.println(el);
            }  
  
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label iID = new Label("ID");
//          grid.add(iID, 0, 0);
          ComboBox ID = new ComboBox();
//          grid.add(ID , 0, 1);
          String SQL = "SELECT ID from student";
          ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(SQL);
	          while(rs.next()) {
	        	  ID.getItems().addAll(rs.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HBox h = new HBox();
		h.getChildren().addAll(iID,ID);  
		h.setSpacing(10);  
        grid.add(h , 0, 0);

        
        Label s = new Label("course_id + sec_id + semester + year");
        grid.add(s, 0,2 );
        ComboBox sec = new ComboBox();
        grid.add(sec, 0, 3);
        String SQL4 = "SELECT course_id, sec_id, semester, year from section";
        ResultSet rs4;
		try {
			rs4 = c.createStatement().executeQuery(SQL4);
	          while(rs4.next()) {
	        	  sec.getItems().addAll(rs4.getString(1) +" "+ rs4.getString(2) +" "+ rs4.getString(3) +" "+ rs4.getString(4));

	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
        Label g = new Label("grade");
//        grid.add(g, 0, 4);
        TextField grade = new TextField();
//        grid.add(grade, 1, 4);
        
		HBox h2 = new HBox();
		h2.getChildren().addAll(g,grade);  
		h2.setSpacing(10);  
        grid.add(h2 , 0, 4);
          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
          Button search = new Button("search");
          
          insert.disableProperty().bind(
        		  Bindings.isEmpty(grade.textProperty())
      		    .or (ID.valueProperty().isNull())
        		  .or(sec.valueProperty().isNull())

      		);


          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 0, 5);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = (String)ID.getSelectionModel().getSelectedItem();
              String a = (String)sec.getSelectionModel().getSelectedItem();
              String[] x =  a.split(" ");
              String a2 = x[0];
              String a3 = x[1];
              String a4 = x[2];
              String a5 = x[3];
              String a6 = grade.getText();



              String query = "insert into takes  values ('"+ 
            		  a1+ "', '"+
            		  a2+ "', '"+
            		  a3+ "', '"+
            		  a4+ "', '"+
            		  a5+ "', '"+
    		          a6+"')";    		
              System.out.println(query);
              
              String s1 = "";
              ResultSet rss;
			try {
				rss = stmt.executeQuery("SELECT * FROM takes");
				while (rss.next()) {                            
					s1 += rss.getString(1)+ rss.getString(2)+ rss.getString(3)+rss.getString(4)+rss.getString(5)+" ";  
					
					System.out.println(s1);
					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(a1+x[0]+x[1]+x[2]+x[3])) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This ID "+ a1 +" and "+a+" and "+ a6+" is in the databse");
      				alert.show();	
              }
     
              else { 
  			
	  			try {
						stmt.executeUpdate(query);
						resultTxt.setText("takes with Id " + a1 + " was added");
	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
          }
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("takes");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          search.setOnAction(z->{
	           
    			  String query="select * from takes ";

    			  String a = null;
    			  
    			  if(ID.getValue() != null || sec.getValue() != null || !grade.getText().isEmpty()) {
    				  query+= "where ";

    				  if (ID.getValue() != null)
    					  query+= " ID = " + ID.getValue()+ " AND";

    				  if (sec.getValue() != null) {
    	                   a = (String)sec.getSelectionModel().getSelectedItem();
                      String[] Q =  a.split(" ");
                      String a2 = Q[0];
                      String a3 = Q[1];
                      String a4 = Q[2];
                      String a5 = Q[3];
                      query+= " course_id = '" + a2 + "' AND sec_id= '"+ a3 + "' AND semester= '"+ a4 + "' AND year= "+ a5+ " AND";
    				  }
    				  
    				  if (!grade.getText().isEmpty())
    					  query+= " grade = '" + grade.getText()+"'";
    				  
    			  }

    			  
    			  if(query.endsWith("AND")) {
    				  query = query.substring(0,query.lastIndexOf(" "));
    			  }
    			  System.out.println(query);
    			  
    			  try {
    				buildQuery(query);
    			} catch (ClassNotFoundException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    			  

    			  try {
    				  Statement stmt2=(Statement)c.createStatement();
    				  ResultSet rs2=stmt2.executeQuery(query);
    				  
    				  tableview.getColumns().clear();
    				  tableview.getItems().clear();
    				  
    			        ObservableList<Object> data = FXCollections.observableArrayList();

    				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
    		                //We are using non property style for making dynamic table
    		                final int j = i;
    		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
    		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
    		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
    		                        return new SimpleStringProperty(param.getValue().get(j).toString());
    		                    }
    		                });
    		 
    		                tableview.getColumns().addAll(col);
    		                System.out.println("Column [" + i + "] ");
    		            }
    		 
    		            /**
    		             * ******************************
    		             * Data added to ObservableList *
    		             *******************************
    		             */
    		            while (rs2.next()) {
    		                //Iterate Row
    		                ObservableList<String> row = FXCollections.observableArrayList();
    		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
    		                    //Iterate Column
    		                    row.add(rs2.getString(i));
    		                }
    		                System.out.println("Row [1] added " + row);
    		                data.add(row);
    		 
    		            }
    		 
    		            //FINALLY ADDED TO TableView
    		            tableview.setItems(data);
    				  
    				  
    			   }catch(SQLException e1) {
    				   System.out.println("");
    			   }
     
    		  });


          dashboardLayout.setCenter(tableview);
     
        });
        
        
        
//        takesBtn.setOnAction(e -> {
//            try {
//                //TableView
//                tableview = new TableView();
//                buildData("takes");
//         
//                //Main Scene
//         
//                dashboardLayout.setCenter(tableview);
//                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));
//
//                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                    @Override
//                    public void handle(WindowEvent event) {
//                        Platform.exit();
//                        System.exit(0);
//                    }
//                });
//            } catch (Exception el) {
//                System.out.println(el);
//            }
//            
//            GridPane grid = new GridPane();
//            grid.setHgap(10);
//            grid.setVgap(10);
//            grid.setPadding(new Insets(60, 25, 25, 25));
//            dashboardLayout.setLeft(grid);
//            
//
//          
//          Label l1 = new Label("ID");
//          grid.add(l1, 0, 0);
//          ComboBox ID = new ComboBox();
//          grid.add(ID, 1, 0);
//          String SQL = "SELECT ID from student";
//          ResultSet rs;
//		try {
//			rs = c.createStatement().executeQuery(SQL);
//	          while(rs.next()) {
//	        	  ID.getItems().addAll(rs.getString(1));
//	          }
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//		
//        Label l2 = new Label("course_id");
//        grid.add(l2, 0, 1);
//        ComboBox course_id = new ComboBox();
//        grid.add(course_id, 1, 1);
//        String SQL2 = "SELECT course_id from section";
//        ResultSet rs2;
//		try {
//			rs2 = c.createStatement().executeQuery(SQL2);
//	          while(rs2.next()) {
//	        	  course_id.getItems().addAll(rs2.getString(1));
//	          }
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//        Label l3 = new Label("sec_id");
//        grid.add(l3, 0, 2);
//        ComboBox sec_id = new ComboBox();
//        grid.add(sec_id, 1, 2);
//        String SQL3 = "SELECT course_id from section WHERE course_id='"+ (String)ID.getSelectionModel().getSelectedItem()+"'";
//        ResultSet rs3;
//		try {
//			rs3 = c.createStatement().executeQuery(SQL3);
//	          while(rs3.next()) {
//	        	  sec_id.getItems().addAll(rs3.getString(1));
//	          }
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//		
//        Label l4 = new Label("semester");
//        grid.add(l4, 0, 3);
//        ComboBox semester = new ComboBox();
//        grid.add(semester, 1, 3);
//        String SQL4 = "SELECT course_id from section WHERE course_id='"+ (String)course_id.getSelectionModel().getSelectedItem()
//        		+"'AND sec_id= '"+(String)sec_id.getSelectionModel().getSelectedItem() +"'" ;
//        ResultSet rs4;
//		try {
//			rs4 = c.createStatement().executeQuery(SQL3);
//	          while(rs4.next()) {
//	        	  semester.getItems().addAll(rs4.getString(1));
//	          }
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//		
//		
////        Label l2 = new Label("section");
////        grid.add(l2, 0, 1);
////        ComboBox section = new ComboBox();
////        grid.add(section, 1, 1);
////        String SQL2 = "SELECT course_id, sec_id, semester, year from section";
////        ResultSet rs2;
////		try {
////			rs2 = c.createStatement().executeQuery(SQL2);
////	          while(rs2.next()) {
////	        	  section.getItems().addAll(rs2.getString(1)+rs2.getString(2)+rs2.getString(3)+rs2.getString(4));
////	          }
////		} catch (SQLException e2) {
////			// TODO Auto-generated catch block
////			e2.printStackTrace();
////		}
//
//          
//          Label l6 = new Label("grade");
//          grid.add(l6, 0, 5);
//          TextField grade = new TextField();
//          grid.add(grade, 1, 5);
//          
//          Button insert = new Button("Insert");
//          HBox hbox = new HBox(10);
//          
//          
////          insert.disableProperty().bind(
////        		    Bindings.isEmpty(grade.textProperty())
////          		    .or(ID.valueProperty().isNull())
////          		    .or(section.valueProperty().isNull())
////        		);
//
//          hbox.setAlignment(Pos.BOTTOM_CENTER);
//          hbox.getChildren().add(insert);
//          grid.add(hbox, 1, 6);
//          
//			TextArea resultTxt = new TextArea();
//			dashboardLayout.setBottom(resultTxt);
//          
//          
//          //insert
////          insert.setOnAction(i -> {
////          	
////              String a1 = grade.getText();
////              String a2 = (String)ID.getSelectionModel().getSelectedItem();
////              String a3 = (String)course_id.getSelectionModel().getSelectedItem();
////              String a4 = (String)sec_id.getSelectionModel().getSelectedItem();
////              String a5= (String)semester.getSelectionModel().getSelectedItem();
////              String a6 = grade.getText();
////
////              String query = "insert into takes  values ('"+ 
////            		  a1+ "', '"+
////    		          a2 +"', '"+ 
////    		          a3 +"')";    		
////              System.out.println(query);
////  			
////  			try {
////					stmt.executeUpdate(query);
////					resultTxt.setText("takes with building " + ID.getSelectionModel().getSelectedItem() +" was added");
////
////				} catch (SQLException e1) {
////					// TODO Auto-generated catch block
////					e1.printStackTrace();
////				}   
////  			
////            try {
////                //TableView
////                tableview = new TableView();
////                buildData("takes");
////         
////                //Main Scene
////         
//////                dashboardLayout.setBottom(tableview); 
////                dashboardLayout.setCenter(tableview);
////                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));
////
////                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
////                    @Override
////                    public void handle(WindowEvent event) {
////                        Platform.exit();
////                        System.exit(0);
////                    }
////                });
////            } catch (Exception el) {
////                System.out.println(el);
////            }
////  			
////          });
//
//          dashboardLayout.setCenter(tableview);
//            
//        
//        });
        

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
        teachesBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("teaches");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }  
            
  
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label iID = new Label("ID");
//          grid.add(iID, 0, 0);
          ComboBox ID = new ComboBox();
//          grid.add(ID , 0, 1);
          String SQL = "SELECT ID from instructor";
          ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(SQL);
	          while(rs.next()) {
	        	  ID.getItems().addAll(rs.getString(1));
	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HBox h = new HBox();
		h.getChildren().addAll(iID,ID);  
		h.setSpacing(10);  
        grid.add(h , 0, 0);

        
        Label s = new Label("course_id + sec_id + semester + year");
        grid.add(s, 0,2 );
        ComboBox sec = new ComboBox();
        grid.add(sec, 0, 3);
        String SQL4 = "SELECT course_id, sec_id, semester, year from section";
        ResultSet rs4;
		try {
			rs4 = c.createStatement().executeQuery(SQL4);
	          while(rs4.next()) {
	        	  sec.getItems().addAll(rs4.getString(1) +" "+ rs4.getString(2) +" "+ rs4.getString(3) +" "+ rs4.getString(4));

	          }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
          Button search = new Button("search");
          
          insert.disableProperty().bind(
        		  (ID.valueProperty().isNull())
        		  .or(sec.valueProperty().isNull())

      		);


          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 0, 4);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = (String)ID.getSelectionModel().getSelectedItem();
              String a = (String)sec.getSelectionModel().getSelectedItem();
              String[] x =  a.split(" ");
              String a2 = x[0];
              String a3 = x[1];
              String a4 = x[2];
              String a5 = x[3];



              String query = "insert into teaches  values ('"+ 
            		  a1+ "', '"+
            		  a2+ "', '"+
            		  a3+ "', '"+
            		  a4+ "', '"+
    		          a5+"')";    		
              System.out.println(query);
              
              String s1 = "";
              ResultSet rss;
			try {
				rss = stmt.executeQuery("SELECT * FROM teaches");
				while (rss.next()) {                            
					s1 += rss.getString(1)+ rss.getString(2)+ rss.getString(3)+rss.getString(4)+rss.getString(5)+" ";  
					
					System.out.println(s1);
					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(a1+x[0]+x[1]+x[2]+x[3])) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This ID "+ a1 +" and "+a+" is in the databse");
      				alert.show();	
              }
     
              
              else {  
            	  try {
						stmt.executeUpdate(query);
						resultTxt.setText("teaches with Id " + a1 + " was added");
	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}   
  			
              }
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("teaches");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          search.setOnAction(z->{
        	  
        	  HBox box =new HBox();
              Label l5 = new Label("course_id");
              TextField course_id = new TextField();

              box.getChildren().addAll(l5,course_id);
              box.setSpacing(10);  
              grid.add(box, 0, 6);

              
        	  HBox box2 =new HBox();
              Label l6 = new Label("sec_id");
              TextField sec_id = new TextField();
              box2.getChildren().addAll(l6,sec_id);
              box2.setSpacing(23); 
              grid.add(box2, 0, 7);

        	  HBox box3 =new HBox();
              Label l7 = new Label("semester");
              TextField semester = new TextField();
              box3.getChildren().addAll(l7,semester);
              box3.setSpacing(10); 
              grid.add(box3, 0, 8);
              
        	  HBox box4 =new HBox();
              Label l8 = new Label("year");
              TextField year = new TextField();
              box4.getChildren().addAll(l8,year);
              box4.setSpacing(37); 
              grid.add(box4, 0, 9);
              
              HBox hbo = new HBox(10);
              Button search2 = new Button("search");
              hbo.setAlignment(Pos.BOTTOM_CENTER);
              hbo.getChildren().addAll(search2);
              grid.add(hbo, 0, 10);
        	  
	           
			  String query="select * from teaches ";

			  String a = null;
			  
			  if(ID.getValue() != null || !course_id.getText().isEmpty() || !sec_id.getText().isEmpty() || !semester.getText().isEmpty() || !year.getText().isEmpty())  {
				  query+= "where ";

				  if (ID.getValue() != null)
					  query+= " ID = " + ID.getValue()+ " AND";

//				  if (sec.getValue() != null) {
//	                   a = (String)sec.getSelectionModel().getSelectedItem();
//                  String[] Q =  a.split(" ");
//                  String a2 = Q[0];
//                  String a3 = Q[1];
//                  String a4 = Q[2];
//                  String a5 = Q[3];
//                  query+= " course_id = '" + a2 + "' AND sec_id= '"+ a3 + "' AND semester= '"+ a4 + "' AND year= "+ a5+ " AND";
//				  }
				  
				  
				  if(!course_id.getText().isEmpty())
					  query+= "course_id = '" + course_id.getText()+ "' AND";

				  if(!sec_id.getText().isEmpty())
					  query+= "sec_id = '" + sec_id.getText()+ "' AND";
				  if(!semester.getText().isEmpty())
					  query+= "semester = '" + semester.getText()+ "' AND";
				  if(!year.getText().isEmpty())
					  query+= "year = '" + year.getText()+ "' AND";
				  
			  }

			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs2=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs2.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs2.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }
			  
			  search2.setOnAction(q->{
	        	  
				  String query2="select * from teaches ";

				  String a2 = null;
				  
				  if(ID.getValue() != null || !course_id.getText().isEmpty() || !sec_id.getText().isEmpty() || !semester.getText().isEmpty() || !year.getText().isEmpty())  {
					  query2+= "where ";

					  if (ID.getValue() != null)
						  query2+= " ID = " + ID.getValue()+ " AND";

//					  if (sec.getValue() != null) {
//		                   a = (String)sec.getSelectionModel().getSelectedItem();
//	                  String[] Q =  a.split(" ");
//	                  String a2 = Q[0];
//	                  String a3 = Q[1];
//	                  String a4 = Q[2];
//	                  String a5 = Q[3];
//	                  query+= " course_id = '" + a2 + "' AND sec_id= '"+ a3 + "' AND semester= '"+ a4 + "' AND year= "+ a5+ " AND";
//					  }
					  
					  
					  if(!course_id.getText().isEmpty())
						  query2+= "course_id = '" + course_id.getText()+ "' AND";

					  if(!sec_id.getText().isEmpty())
						  query2+= "sec_id = '" + sec_id.getText()+ "' AND";
					  if(!semester.getText().isEmpty())
						  query2+= "semester = '" + semester.getText()+ "' AND";
					  if(!year.getText().isEmpty())
						  query2+= "year = '" + year.getText()+ "' AND";
					  
				  }

				  
				  if(query2.endsWith("AND")) {
					  query2 = query2.substring(0,query2.lastIndexOf(" "));
				  }
				  System.out.println(query2);
				  
				  try {
					buildQuery(query2);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  

				  try {
					  Statement stmt2=(Statement)c.createStatement();
					  ResultSet rs2=stmt2.executeQuery(query2);
					  
					  tableview.getColumns().clear();
					  tableview.getItems().clear();
					  
				        ObservableList<Object> data = FXCollections.observableArrayList();

					  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
			                //We are using non property style for making dynamic table
			                final int j = i;
			                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
			                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
			                        return new SimpleStringProperty(param.getValue().get(j).toString());
			                    }
			                });
			 
			                tableview.getColumns().addAll(col);
			                System.out.println("Column [" + i + "] ");
			            }
			 
			            /**
			             * ******************************
			             * Data added to ObservableList *
			             *******************************
			             */
			            while (rs2.next()) {
			                //Iterate Row
			                ObservableList<String> row = FXCollections.observableArrayList();
			                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
			                    //Iterate Column
			                    row.add(rs2.getString(i));
			                }
			                System.out.println("Row [1] added " + row);
			                data.add(row);
			 
			            }
			 
			            //FINALLY ADDED TO TableView
			            tableview.setItems(data);
					  
					  
				   }catch(SQLException e1) {
					   System.out.println("");
				   }
	 
			  });

			  
			  
			  
			  
			  
			  
			  
			  
 
		  });



          dashboardLayout.setCenter(tableview);
     
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////  


        
        time_slotBtn.setOnAction(e -> {
            try {
                //TableView
                tableview = new TableView();
                buildData("time_slot");
         
                //Main Scene
         
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(60, 25, 25, 25));
            dashboardLayout.setLeft(grid);
            
            
          Label l1 = new Label("time_slot_id");
          grid.add(l1, 0, 0);
          TextField time_slot_id  = new TextField(); 
          grid.add(time_slot_id, 1, 0);

          
          
          Label l2 = new Label("day");
          grid.add(l2, 0, 1);
          TextField day = new TextField();
          grid.add(day, 1, 1);
          

          Label l3 = new Label("start_hr");
          grid.add(l3, 0, 2);
          TextField start_hr = new TextField();
          grid.add(start_hr, 1, 2);
          
          Label l4 = new Label("start_min");
          grid.add(l4, 0, 3);
          TextField start_min = new TextField();
          grid.add(start_min, 1, 3);
          
          Label l5 = new Label("end_hr");
          grid.add(l5, 0, 4);
          TextField end_hr = new TextField();
          grid.add(end_hr, 1, 4);
          
          Label l6 = new Label("end_min");
          grid.add(l6, 0, 5);
          TextField end_min = new TextField();
          grid.add(end_min, 1, 5);
          
          
          
          Button insert = new Button("Insert");
          HBox hbox = new HBox(10);
          Button search = new Button("search");
          
          
          insert.disableProperty().bind(
        		    Bindings.isEmpty(time_slot_id.textProperty())
        		    .or(Bindings.isEmpty(day.textProperty()))
        		    .or(Bindings.isEmpty(start_hr.textProperty()))
        		    .or(Bindings.isEmpty(start_min.textProperty()))
        		    .or(Bindings.isEmpty(end_hr.textProperty()))
        		    .or(Bindings.isEmpty(end_min.textProperty()))

        		);

          hbox.setAlignment(Pos.BOTTOM_CENTER);
          hbox.getChildren().addAll(insert,search);
          grid.add(hbox, 1, 6);
          
			TextArea resultTxt = new TextArea();
			dashboardLayout.setBottom(resultTxt);
          
          
          //insert
          insert.setOnAction(i -> {
          	
              String a1 = time_slot_id.getText();
              String a2 = day.getText();
              String a3 =  start_hr.getText();
              String a4 =  start_min.getText();
              String a5 =  end_hr.getText();
              String a6 =  end_min.getText();

              String query = "insert into time_slot  values ('"+ 
            		  a1+ "', '"+
    		          a2 +"', '"+ 
    		          a3 +"', '"+
    		          a4 +"', '"+
    		          a5 +"', '"+
    		          a6+"')";    		
              System.out.println(query);
              
              String s1 = "";
              ResultSet rs2;
			try {
				rs2 = stmt.executeQuery("SELECT time_slot_id, day, start_hr, start_min FROM time_slot");
				while (rs2.next()) {                            
					s1 += rs2.getString(1)+ rs2.getString(2)+ rs2.getString(3)+ rs2.getString(4) +" ";  
					
					System.out.println(s1);
					} 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}                     
	             
              
              if(s1.contains(time_slot_id.getText()+day.getText()+start_hr.getText()+start_min.getText())) {
    				alert.setAlertType(AlertType.WARNING);
      				alert.setContentText("This time_slot_id "+ a1+" and day "+ a2
      										+" and start_hr "+ a3 +" and start_min "+ a4
      										+" are in the databse");
      				alert.show();	
              }
              
              else if(!isNumber(start_hr.getText())) {
    				alert.setAlertType(AlertType.WARNING);
    				alert.setContentText("start_hr must be number");
    				alert.show();			
                    }
    			else if(!isNumber(start_min.getText())) {
    				alert.setAlertType(AlertType.WARNING);
    				alert.setContentText("start_min must be number");
    				alert.show();			
                    }
    			else if(!isNumber(end_hr.getText())) {
    				alert.setAlertType(AlertType.WARNING);
    				alert.setContentText("end_hr must be number");
    				alert.show();			
                    }
    			else if(!isNumber(end_min.getText())) {
    				alert.setAlertType(AlertType.WARNING);
    				alert.setContentText("end_min must be number");
    				alert.show();			
                    }
      			else {
		  			
		  			try {
							stmt.executeUpdate(query);
							resultTxt.setText("time_slot with time_slot_id " + time_slot_id.getText() +" was added");
		
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}   
  			
      			}
  			
            try {
                //TableView
                tableview = new TableView();
                buildData("time_slot");
         
                //Main Scene
         
//                dashboardLayout.setBottom(tableview); 
                dashboardLayout.setCenter(tableview);
                BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            } catch (Exception el) {
                System.out.println(el);
            }
  			
          });
          
          search.setOnAction(z->{
	           
			  String query="select * from time_slot ";

			  
			  
			  if(!time_slot_id.getText().isEmpty() || !day.getText().isEmpty() ||!start_hr.getText().isEmpty()  || !start_min.getText().isEmpty()
					  || !end_hr.getText().isEmpty() || !end_hr.getText().isEmpty()  ) {
				  query+= "where ";
				  
				  
				  if (!time_slot_id.getText().isEmpty())
					  query+= "time_slot_id = '" + time_slot_id.getText()+ "' AND";
				  if (!day.getText().isEmpty())
					  query+= " day = '" + day.getText()+ "' AND";
				  if (!start_hr.getText().isEmpty())
					  query+= " start_hr = " + start_hr.getText()+ " AND";
				  if (!start_min.getText().isEmpty())
					  query+= " start_min = " + start_min.getText()+ " AND";
				  if (!end_hr.getText().isEmpty())
					  query+= " end_hr = " + end_hr.getText()+ " AND";
				  if (!end_min.getText().isEmpty())
					  query+= " end_min = " + end_min.getText();
				  
			  }

			  
			  if(query.endsWith("AND")) {
				  query = query.substring(0,query.lastIndexOf(" "));
			  }
			  System.out.println(query);
			  
			  try {
				buildQuery(query);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  

			  try {
				  Statement stmt2=(Statement)c.createStatement();
				  ResultSet rs2=stmt2.executeQuery(query);
				  
				  tableview.getColumns().clear();
				  tableview.getItems().clear();
				  
			        ObservableList<Object> data = FXCollections.observableArrayList();

				  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
		                //We are using non property style for making dynamic table
		                final int j = i;
		                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
		                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
		                        return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
		                });
		 
		                tableview.getColumns().addAll(col);
		                System.out.println("Column [" + i + "] ");
		            }
		 
		            /**
		             * ******************************
		             * Data added to ObservableList *
		             *******************************
		             */
		            while (rs2.next()) {
		                //Iterate Row
		                ObservableList<String> row = FXCollections.observableArrayList();
		                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
		                    //Iterate Column
		                    row.add(rs2.getString(i));
		                }
		                System.out.println("Row [1] added " + row);
		                data.add(row);
		 
		            }
		 
		            //FINALLY ADDED TO TableView
		            tableview.setItems(data);
				  
				  
			   }catch(SQLException e1) {
				   System.out.println("");
			   }
			  
			  
			 
			  
			  
		  });

          dashboardLayout.setCenter(tableview);
            
        
        });


        
        sideBar.getChildren().addAll(advisorBtn, classroomBtn, courseBtn, departmentBtn, instructorBtn, prereqBtn, 
        		sectionBtn, studentBtn, takesBtn, teachesBtn, time_slotBtn);
        sideBar.setSpacing(10);

        
        
        stage.setScene(dashboardScene);
        stage.show();

        




	}
	
	
    public void buildData(String s) {
        data = FXCollections.observableArrayList();
        try {
    		Class.forName("com.mysql.jdbc.Driver");  
        	Connection c=DriverManager.getConnection(  
        			"jdbc:mysql://localhost:3306/Lab77","root",""); 
 
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from " + s;
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
 
            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
 
                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
 
            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
 
            }
 
            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    
    
    public void buildQuery(String q) throws ClassNotFoundException {
        data = FXCollections.observableArrayList();
        try {
    		Class.forName("com.mysql.jdbc.Driver");  
        	Connection c=DriverManager.getConnection(  
        			"jdbc:mysql://localhost:3306/Lab77","root",""); 
 
		  Statement stmt2=(Statement)c.createStatement();
		  ResultSet rs2=stmt2.executeQuery(q);
		  
	        ObservableList<Object> data = FXCollections.observableArrayList();

		  for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
              //We are using non property style for making dynamic table
              final int j = i;
              TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
              col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                  public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                      return new SimpleStringProperty(param.getValue().get(j).toString());
                  }
              });

              tableview.getColumns().addAll(col);
              System.out.println("Column [" + i + "] ");
          }

          /**
           * ******************************
           * Data added to ObservableList *
           *******************************
           */
          while (rs2.next()) {
              //Iterate Row
              ObservableList<String> row = FXCollections.observableArrayList();
              for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
                  //Iterate Column
                  row.add(rs2.getString(i));
              }
              System.out.println("Row [1] added " + row);
              data.add(row);

          }

          //FINALLY ADDED TO TableView
          tableview.setItems(data);
		  
		  
	   }catch(SQLException e1) {
		   System.out.println("");
	   }
    }
	
	public static boolean isNumber(String s) throws NumberFormatException {

		try {
			Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
		

	}
}




//
//courseBtn.setOnAction(e -> {
//    try {
//        //TableView
//        tableview = new TableView();
//        buildData("course");
// 
//        //Main Scene
// 
//        dashboardLayout.setCenter(tableview);
//        BorderPane.setMargin(tableview, new Insets(30, 30, 100, 30));
//
//        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                Platform.exit();
//                System.exit(0);
//            }
//        });
//    } catch (Exception el) {
//        System.out.println(el);
//    }
//
//});

