
package ca.ubc.cs.cpsc210.ui;


import ca.ubc.cs.cpsc210.model.exceptions.InvalidName;
import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.reminders.*;
import ca.ubc.cs.cpsc210.model.userdata.userprofile.UserProfile;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

import static ca.ubc.cs.cpsc210.persistence.JsonForHealthCare.usertojson;
import static ca.ubc.cs.cpsc210.persistence.JsonForReminders.tasklisttojson;

public class Controller {

    UserProfile user;
    Project project;


    @FXML StackPane mainStackPane, profilestackpane, basicinfostack, hcStack, bpStack, hrStack;
    @FXML StackPane medStack, vitStack, vacStack, remStack;
    @FXML SplitPane mainmenupane;
    @FXML Pane welcomepane, addstuffpane;
    @FXML ScrollPane basicinfopane, bloodpressurepane, healthcondpane, heartratepane;
    @FXML ScrollPane medicationpane, reminderspane, vaccpane, vitaminpane;
    @FXML Button editbasicinfo;
    @FXML AnchorPane basicinfodisplay, basicinfoedit;
    @FXML AnchorPane viewHCpane, addHCpane, bpView, bpEdit, hrView, hrEdit, medView, medEdit;
    @FXML AnchorPane vitView, vitEdit, vacView, vacEdit, remView, remEdit;
    @FXML TextField enteredlname, enteredfname, enteredheight, enteredweight, enteredwaistcirc, enteredage;
    @FXML Label userage, userfirstname, userlastname, userheight, userwaistcirc, userweight;

    @FXML TableView<HealthConditions> hcTable;
    @FXML TableColumn<HealthConditions, String> diagnosiscol = new TableColumn<>();
    @FXML TableColumn<HealthConditions, String> diagdatecol = new TableColumn<>();
    @FXML TableColumn<HealthConditions, String> diagcommentcol = new TableColumn<>();
    @FXML TextField ediagyear, ediagmonth, ediagday, ediagcom, enterdiagname;

    @FXML TableView<BloodPressure> bpTable;
    @FXML TableColumn<BloodPressure, Integer> diacol = new TableColumn<>();
    @FXML TableColumn<BloodPressure, Integer> syscol = new TableColumn<>();
    @FXML TableColumn<BloodPressure, String> bpdatcol = new TableColumn<>();
    @FXML TableColumn<BloodPressure, String> bpcomcol = new TableColumn<>();
    @FXML TextField entersys, enterdias, ebpcom, ebpday, ebpmonth, ebpyear;

    @FXML TableView<HeartRate> hrTable;
    @FXML TableColumn<HeartRate, Integer> hrcol = new TableColumn<>();
    @FXML TableColumn<HeartRate, String> hrdatecol = new TableColumn<>();
    @FXML TableColumn<HeartRate, String> hrcomcol = new TableColumn<>();
    @FXML TextField ehr, ehrcom, ehrday, ehrmonth, ehryear;

    @FXML TableView<Medications> medTable;
    @FXML TableColumn<Medications, String> mendcol = new TableColumn<>();
    @FXML TableColumn<Medications, Double> mfreqcol = new TableColumn<>();
    @FXML TableColumn<Medications, String> mnamecol = new TableColumn<>();
    @FXML TableColumn<Medications, String> mstartcol = new TableColumn<>();
    @FXML TableColumn<Medications, Boolean> mtakingcol = new TableColumn<>();
    @FXML TableColumn<Medications, String> mtreatscol = new TableColumn<>();
    @FXML TableColumn<Medications, String> mdosecol = new TableColumn<>();
    @FXML TextField emcom, emdaystar, emdaystop, emdose, emfreq, emmonstar, emmonstop, emname, emuf;
    @FXML TextField emyearstar, emyearstop;
    @FXML RadioButton emno, emyes;

    @FXML TableView<Vitamins> vitTable;
    @FXML TableColumn<Vitamins, String> vendcol = new TableColumn<>();
    @FXML TableColumn<Vitamins, Double> vfreqcol = new TableColumn<>();
    @FXML TableColumn<Vitamins, String> vnamecol = new TableColumn<>();
    @FXML TableColumn<Vitamins, String> vstartcol = new TableColumn<>();
    @FXML TableColumn<Vitamins, Boolean> vtakingcol = new TableColumn<>();
    @FXML TableColumn<Vitamins, String> vtreatscol = new TableColumn<>();
    @FXML TableColumn<Vitamins, String> vdosecol = new TableColumn<>();
    @FXML TextField evcom, evdaystar, evdaystop, evdose, evfreq, evmonstar,evmonstop;
    @FXML TextField evname, evuf, evyearstar,evyearstop;
    @FXML RadioButton evno, evyes;

    @FXML TableView<Vaccinations> vacTable;
    @FXML TableColumn<Vaccinations, String> vaccol = new TableColumn<>();
    @FXML TableColumn<Vaccinations, String> vacdatecol = new TableColumn<>();
    @FXML TextField evacname, evacday, evacmonth,evacyear;

    @FXML TableView<Task> remTable;
    @FXML TableColumn<Task, String> remdatecol = new TableColumn<>();
    @FXML TableColumn<Task, String> taskcol = new TableColumn<>();
    @FXML TableColumn<Task, String> pricol = new TableColumn<>();
    @FXML TableColumn<Task, String> statuscol = new TableColumn<>();
    @FXML TableColumn<Task, String> tagcol = new TableColumn<>();
    @FXML TextField erdes, erday,ermonth, eryear,erhr,ermin,ertag;
    @FXML RadioButton eruryes, erimyes, ertodo,erinprog, erdone;


    //EFFECTS: saves user profile and projects
    public void saveButton() {
        saveUser();
        saveProjs();
    }

    //EFFECTS: saves user profile
    public void saveUser() {
        String fileName = "medapp.json";

        JSONObject userprofile = usertojson(user);
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(userprofile.toString());
            file.close();
            System.out.println("wrote file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: saves user projects
    public void saveProjs() {
        String fileName = "proj.json";

        JSONArray tasks = (tasklisttojson((ArrayList<Task>) project.getTasks()));
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(tasks.toString());
            file.close();
            System.out.println("wrote file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: Enter button click: enter to main menu page
    public void enterButtonClicked() {
        System.out.println("going to main menu");
        welcomepane.setVisible(false);
        mainmenupane.setVisible(true);
        setAllChildrenFalse(profilestackpane);
        addstuffpane.setVisible(true);
    }

    //EFFECTS: Goes to basic Info button click
    public void basicInfoButton() {
        System.out.println("going to basic info");
        setAllChildrenFalse(profilestackpane);
        basicinfopane.setVisible(true);
        basicinfodisplay.setVisible(true);
    }

    //EFFECTS: goes to bp Info on button click
    public void bpButton() {
        System.out.println("going to bp info");
        setAllChildrenFalse(profilestackpane);
        bloodpressurepane.setVisible(true);
    }

    //EFFECTS: go to health condition info on button click
    public void healthcondpButton() {
        System.out.println("going to health cond info");
        setAllChildrenFalse(profilestackpane);
        healthcondpane.setVisible(true);
        setAllChildrenFalse(hcStack);
        viewHCpane.setVisible(true);
    }

    //EFFECTS: goes to hr info on button click
    public void hrButton() {
        System.out.println("going to hr info");
        setAllChildrenFalse(profilestackpane);
        heartratepane.setVisible(true);
    }

    //EFFECTS: goes to med info on button click
    public void medButton() {
        System.out.println("going to med info");
        setAllChildrenFalse(profilestackpane);
        medicationpane.setVisible(true);
    }

    //EFFECTS: goes to reminders info on button click
    public void reminderButton() {
        System.out.println("going to reminders info");
        setAllChildrenFalse(profilestackpane);
        reminderspane.setVisible(true);
    }

    //EFFECTS: goes to vac info on button click
    public void vacButton() {
        System.out.println("going to vac info");
        setAllChildrenFalse(profilestackpane);
        vaccpane.setVisible(true);
    }

    //EFFECTS: goes to vit info on button click
    public void vitButton() {
        System.out.println("going to vit info");
        setAllChildrenFalse(profilestackpane);
        vitaminpane.setVisible(true);
    }

    //EFFECTS: go to edit basic info
    public void editBasicInfoButton() {
        System.out.println("going to edit basic info");
        setAllChildrenFalse(profilestackpane);
        basicinfopane.setVisible(true);
        basicinfostack.setVisible(true);
        basicinfodisplay.setVisible(false);
        basicinfoedit.setVisible(true);
    }

    //EFFECTS: done editing basic info, creates user and sets info
    public void doneEditBasicInfoButton() throws IOException {
        try {
            setBasicInfo();
        } catch (InvalidName invalidName) {
            System.out.println("Please enter a valid first name");
            errorPopUpWindow();
            return;
        } catch (InvalidMeasurement invalidMeasurement) {
            System.out.println("Please enter a non-zero value");
            errorPopUpWindow();
            return;
        }
        setAllChildrenFalse(profilestackpane);
        setAllChildrenFalse(basicinfostack);
        basicinfopane.setVisible(true);
        basicinfoedit.setVisible(false);
        basicinfodisplay.setVisible(true);
    }

    //MODIFIES: user, project
    //EFFECTS:  edits user and creates projects
    public void setBasicInfo() throws InvalidName, InvalidMeasurement {
        int age = Integer.parseInt(enteredage.getText());
        user = new UserProfile(enteredfname.getText(), enteredlname.getText(),age);
        Main.setUser(user);
        project = new Project(enteredfname.getText() + "'s Project");
        Main.setProject(project);
        user.setHeight(Integer.parseInt(enteredheight.getText()));
        user.setWeight(Integer.parseInt(enteredweight.getText()));
        user.setWaistcirc(Integer.parseInt(enteredwaistcirc.getText()));
        userfirstname.setText(user.getFirstName());
        userlastname.setText(user.getLastName());
        userage.setText(Integer.toString(user.getAge()));
        userheight.setText(Double.toString(user.getHeight()));
        userweight.setText(Double.toString(user.getWeight()));
        userwaistcirc.setText(Double.toString(user.getWaistCirc()));

    }

    //EFFECTS: go to add health condition
    public void editHCButton() {
        System.out.println("going to edit hc");
        setAllChildrenFalse(profilestackpane);
        healthcondpane.setVisible(true);
        viewHCpane.setVisible(false);
        addHCpane.setVisible(true);
    }

    //EFFECTS: go to add health condition
    public void doneEditHCButton() {
        System.out.println("done edit hc");
        setAllChildrenFalse(profilestackpane);
        setAllChildrenFalse(hcStack);
        setHCDetails();
        ObservableList<HealthConditions> healthconds = FXCollections.observableList(user.getHCList());
        diagnosiscol.setCellValueFactory(cellData -> cellData.getValue().hcName());
        diagcommentcol.setCellValueFactory(cellData -> cellData.getValue().hcComment());
        diagdatecol.setCellValueFactory(cellData -> cellData.getValue().hcDate());
        hcTable.setItems(healthconds);
        healthcondpane.setVisible(true);
        viewHCpane.setVisible(true);
        addHCpane.setVisible(false);
    }

    //MODIFIES: user
    //EFFECTS: sets heart condition details
    public void setHCDetails() {
        String name = enterdiagname.getText();
        String comm = ediagcom.getText();
        int d = Integer.parseInt(ediagday.getText());
        int m = Integer.parseInt(ediagmonth.getText());
        int y = Integer.parseInt(ediagyear.getText());
        HealthConditions hc = new HealthConditions(name,comm);
        hc.setDiagnosisDate(y,m,d);
        user.addHC(hc);
    }

    //MODIFIES: user
    //EFFECTS: goes to add bp measure
    public void editBPButton() {
        System.out.println("going to edit bp");
        setAllChildrenFalse(profilestackpane);
        bloodpressurepane.setVisible(true);
        bpView.setVisible(false);
        bpEdit.setVisible(true);
    }

    //MODIFIES: user
    //EFFECTS: adds bp measure to user and display
    public void doneEditBPButton() throws IOException {
        System.out.println("done edit bp");
        setAllChildrenFalse(profilestackpane);
        setAllChildrenFalse(bpStack);
        setBPDetails();
        ObservableList<BloodPressure> bps = FXCollections.observableList(user.getBPList());
        syscol.setCellValueFactory(cellData -> cellData.getValue().bpSys().asObject());
        bpcomcol.setCellValueFactory(cellData -> cellData.getValue().bpComment());
        bpdatcol.setCellValueFactory(cellData -> cellData.getValue().bpDate());
        diacol.setCellValueFactory(cellData -> cellData.getValue().bpDias().asObject());
        bpTable.setItems(bps);
        bloodpressurepane.setVisible(true);
        bpView.setVisible(true);
        bpEdit.setVisible(false);
    }

    //MODIFIES: user
    //EFFECTS: adds bp measure to user
    public void setBPDetails() throws IOException {
        String comment = ebpcom.getText();
        Integer sys = Integer.parseInt(entersys.getText());
        Integer dia = Integer.parseInt(enterdias.getText());
        int d = Integer.parseInt(ebpday.getText());
        int m = Integer.parseInt(ebpmonth.getText());
        int y = Integer.parseInt(ebpyear.getText());
        BloodPressure bp = null;
        try {
            bp = new BloodPressure(sys,dia,comment);
        } catch (InvalidMeasurement invalidMeasurement) {
            System.out.println("Please enter nonzero values");
            errorPopUpWindow();
            return;
        }
        bp.setBpMeasurementDate(y,m,d);
        user.addBP(bp);
    }



    //EFFECTS: goes to edit HR
    public void editHRButton() {
        System.out.println("going to edit hr");
        setAllChildrenFalse(profilestackpane);
        heartratepane.setVisible(true);
        hrView.setVisible(false);
        hrEdit.setVisible(true);
    }

    //MODIFIES: user
    //EFFECTS: adds hr measure to user and display
    public void doneEditHRButton() throws IOException {
        System.out.println("done edit hr");
        setAllChildrenFalse(profilestackpane);
        setAllChildrenFalse(hrStack);
        setHRDetails();
        ObservableList<HeartRate> hrs = FXCollections.observableList(user.getHRList());
        hrcol.setCellValueFactory(cellData -> cellData.getValue().hrVal().asObject());
        hrcomcol.setCellValueFactory(cellData -> cellData.getValue().hrComment());
        hrdatecol.setCellValueFactory(cellData -> cellData.getValue().hrDate());
        hrTable.setItems(hrs);
        heartratepane.setVisible(true);
        hrView.setVisible(true);
        hrEdit.setVisible(false);
    }

    //MODIFIES: user
    //EFFECTS: adds hr measure to user
    public void setHRDetails() throws IOException {
        String comment = ehrcom.getText();
        Integer hr = Integer.parseInt(ehr.getText());
        int d = Integer.parseInt(ehrday.getText());
        int m = Integer.parseInt(ehrmonth.getText());
        int y = Integer.parseInt(ehryear.getText());
        HeartRate hr1 = null;
        try {
            hr1 = new HeartRate(hr,comment);
        } catch (InvalidMeasurement invalidMeasurement) {
            System.out.println("Please enter a nonzero measure");
            errorPopUpWindow();
            return;
        }
        hr1.setHrMeasurementDate(y,m,d);
        user.addHR(hr1);
    }

    //EFFECTS: go to edit meds
    public void editMedButton() {
        System.out.println("going to edit med");
        setAllChildrenFalse(profilestackpane);
        medicationpane.setVisible(true);
        medView.setVisible(false);
        medEdit.setVisible(true);
    }

    //MODIFIES: user
    //EFFECTS: adds meds to user and display
    public void doneEditMedButton() {
        System.out.println("done edit med");
        setAllChildrenFalse(profilestackpane);
        setAllChildrenFalse(medStack);
        setMedDetails();
        ObservableList<Medications> meds = FXCollections.observableList(user.getMedList());
        mendcol.setCellValueFactory(cellData -> cellData.getValue().mvEndDate());
        mfreqcol.setCellValueFactory(cellData -> cellData.getValue().mvPDay().asObject());
        mnamecol.setCellValueFactory(cellData -> cellData.getValue().mvName());
        mstartcol.setCellValueFactory(cellData -> cellData.getValue().mvStartDate());
        mtakingcol.setCellValueFactory(cellData -> cellData.getValue().mvStatus().asObject());
        mtreatscol.setCellValueFactory(cellData -> cellData.getValue().mvTreats());
        mdosecol.setCellValueFactory(cellData -> cellData.getValue().mvDose());
        medTable.setItems(meds);
        medicationpane.setVisible(true);
        medView.setVisible(true);
        medEdit.setVisible(false);
    }

    //MODIFIES: user
    //EFFECTS: adds meds to user
    public void setMedDetails() {
        int d1 = Integer.parseInt(emdaystar.getText());
        int m1 = Integer.parseInt(emmonstar.getText());
        int y1 = Integer.parseInt(emyearstar.getText());
        int d2 = Integer.parseInt(emdaystop.getText());
        int m2 = Integer.parseInt(emmonstop.getText());
        int y2 = Integer.parseInt(emyearstop.getText());
        Boolean s = emyes.isSelected();
        int mvperday = Integer.parseInt((emfreq.getText()));
        Medications med1 = new Medications(emname.getText(), emuf.getText(), emdose.getText(), mvperday, s);
        med1.setmvDateStart(d1,m1,y1);
        med1.setmvDateStop(d2,m2,y2);
        user.addMed(med1);
    }

    //EFFECTS: goes to edit vitamins page
    public void editVitButton() {
        System.out.println("going to edit vits");
        setAllChildrenFalse(profilestackpane);
        vitaminpane.setVisible(true);
        vitView.setVisible(false);
        vitEdit.setVisible(true);
    }

    //MODIFIES: user
    //EFFECTS: adds vits to user and display
    public void doneEditVitButton() {
        System.out.println("done edit vit");
        setAllChildrenFalse(profilestackpane);
        setAllChildrenFalse(vitStack);
        setVitDetails();
        ObservableList<Vitamins> vits = FXCollections.observableList(user.getVitList());
        vendcol.setCellValueFactory(cellData -> cellData.getValue().mvEndDate());
        vfreqcol.setCellValueFactory(cellData -> cellData.getValue().mvPDay().asObject());
        vnamecol.setCellValueFactory(cellData -> cellData.getValue().mvName());
        vstartcol.setCellValueFactory(cellData -> cellData.getValue().mvStartDate());
        vtakingcol.setCellValueFactory(cellData -> cellData.getValue().mvStatus().asObject());
        vtreatscol.setCellValueFactory(cellData -> cellData.getValue().mvTreats());
        vdosecol.setCellValueFactory(cellData -> cellData.getValue().mvDose());
        vitTable.setItems(vits);
        vitaminpane.setVisible(true);
        vitView.setVisible(true);
        vitEdit.setVisible(false);
    }

    //MODIFIES: user
    //EFFECTS: adds vits to user
    public void setVitDetails() {
        int d1 = Integer.parseInt(evdaystar.getText());
        int m1 = Integer.parseInt(evmonstar.getText());
        int y1 = Integer.parseInt(evyearstar.getText());
        int d2 = Integer.parseInt(evdaystop.getText());
        int m2 = Integer.parseInt(evmonstop.getText());
        int y2 = Integer.parseInt(evyearstop.getText());
        Boolean s = evyes.isSelected();
        int mvperday = Integer.parseInt((evfreq.getText()));
        Vitamins vit1 = new Vitamins(evname.getText(), evuf.getText(), evdose.getText(), mvperday, s);
        vit1.setmvDateStart(d1,m1,y1);
        vit1.setmvDateStop(d2,m2,y2);
        user.addVit(vit1);
    }


    //EFFECTS: goes to edit vaccinations
    public void editVacButton() {
        System.out.println("going to edit vacs");
        setAllChildrenFalse(profilestackpane);
        vaccpane.setVisible(true);
        vacView.setVisible(false);
        vacEdit.setVisible(true);
    }

    //MODIFIES: user
    //EFFECTS: adds vacs to user and display
    public void doneEditVacButton() {
        System.out.println("done edit vac");
        setAllChildrenFalse(profilestackpane);
        setAllChildrenFalse(vacStack);
        setVacDetails();
        ObservableList<Vaccinations> vacs = FXCollections.observableList(user.getVacList());
        vaccol.setCellValueFactory(cellData -> cellData.getValue().vacName());
        vacdatecol.setCellValueFactory(cellData -> cellData.getValue().vacDate());
        vacTable.setItems(vacs);
        vaccpane.setVisible(true);
        vacView.setVisible(true);
        vacEdit.setVisible(false);
    }

    //MODIFIES: user
    //EFFECTS: adds vacs to user
    public void setVacDetails() {
        int d1 = Integer.parseInt(evacday.getText());
        int m1 = Integer.parseInt(evacmonth.getText());
        int y1 = Integer.parseInt(evacyear.getText());
        Vaccinations vac1 = new Vaccinations(evacname.getText());
        vac1.setVacDate(d1,m1,y1);
        user.addVac(vac1);
    }


    //EFFECTS: go to add reminders
    public void editRemButton() {
        System.out.println("going to edit reminders");
        setAllChildrenFalse(profilestackpane);
        reminderspane.setVisible(true);
        remView.setVisible(false);
        remEdit.setVisible(true);
    }

    //MODIFIES: project
    //EFFECTS: adds task to project and display
    public void doneEditRemButton() {
        System.out.println("done edit reminders");
        setAllChildrenFalse(profilestackpane);
        setAllChildrenFalse(remStack);
        setRemDetails();
        ObservableList<Task> rems = FXCollections.observableList(project.getTasks());
        taskcol.setCellValueFactory(cellData -> cellData.getValue().taskDesc());
        remdatecol.setCellValueFactory(cellData -> cellData.getValue().taskDD());
        pricol.setCellValueFactory(cellData -> cellData.getValue().taskPriority());
        statuscol.setCellValueFactory(cellData -> cellData.getValue().taskStat());
        tagcol.setCellValueFactory(cellData -> cellData.getValue().taskTag());
        remTable.setItems(rems);
        reminderspane.setVisible(true);
        remView.setVisible(true);
        remEdit.setVisible(false);
    }

    //MODIFIES: project
    //EFFECTS: adds task to project
    public void setRemDetails() {
        int d1 = Integer.parseInt(erday.getText());
        int m1 = Integer.parseInt(ermonth.getText());
        int y1 = Integer.parseInt(eryear.getText());
        int hr = Integer.parseInt(erhr.getText());
        int mins = Integer.parseInt(ermin.getText());
        Task task1 = new Task(erdes.getText());
        Priority pri = new Priority();
        pri.setImportant(erimyes.isSelected());
        pri.setUrgent(eruryes.isSelected());
        Calendar c = Calendar.getInstance();
        c.set(y1,m1,d1,hr,mins);
        task1.setDueDate(new DueDate(c.getTime()));
        task1.addTag(ertag.getText());
        task1.setStatus(buttonToStatus());
        project.add(task1);
    }

    //EFFECTS: determines status based on user button press
    public Status buttonToStatus() {
        if (ertodo.isSelected()) {
            return Status.TODO;
        } else if (erinprog.isSelected()) {
            return Status.IN_PROGRESS;
        }
        return Status.DONE;
    }

    //MODIFIES: user
    //EFFECTS: removes HR from user
    public void deleteHC() {
        int selectedIndex = hcTable.getSelectionModel().getSelectedIndex();
        hcTable.getItems().remove(selectedIndex);
    }

    //MODIFIES: user
    //EFFECTS: removes HR from user
    public void deleteMed() {
        int selectedIndex = medTable.getSelectionModel().getSelectedIndex();
        medTable.getItems().remove(selectedIndex);
    }

    //MODIFIES: user
    //EFFECTS: removes BP from user
    public void deleteBP() {
        int selectedIndex = bpTable.getSelectionModel().getSelectedIndex();
        bpTable.getItems().remove(selectedIndex);
    }


    //MODIFIES: user
    //EFFECTS: removes HR from user
    public void deleteHR() {
        int selectedIndex = hrTable.getSelectionModel().getSelectedIndex();
        hrTable.getItems().remove(selectedIndex);
    }

    //MODIFIES: user
    //EFFECTS: removes Vac from user
    public void deleteVac() {
        int selectedIndex = vacTable.getSelectionModel().getSelectedIndex();
        vacTable.getItems().remove(selectedIndex);
    }

    //MODIFIES: user
    //EFFECTS: removes Vit from user
    public void deleteVit() {
        int selectedIndex = vitTable.getSelectionModel().getSelectedIndex();
        vitTable.getItems().remove(selectedIndex);
    }


    //MODIFIES: project
    //EFFECTS: removes task from project
    public void deleteTask() {
        int selectedIndex = remTable.getSelectionModel().getSelectedIndex();
        remTable.getItems().remove(selectedIndex);
    }

    //EFFECTS: sets stackpane children to false
    public void setAllChildrenFalse(StackPane pane) {
        for (Node p: pane.getChildren()) {
            p.setVisible(false);
        }
    }

    //EFFECTS: creates new error window
    public void errorPopUpWindow() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("invalidinputswindow.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Error");
        stage.setScene(new Scene(root));
        stage.show();
    }

}

