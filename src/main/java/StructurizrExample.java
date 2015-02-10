import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.io.json.JsonWriter;
import com.structurizr.model.Location;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;

import java.io.StringWriter;

/**
 * This is a simple example of how to get started with Structurizr for Java.
 */
public class StructurizrExample {

    public static void main(String[] args) throws Exception {
        Workspace workspace = new Workspace("Financial Risk System", "This is a simple (incomplete) example C4 model based upon the financial risk system architecture kata, which can be found at http://bit.ly/sa4d-risksystem");
        Model model = workspace.getModel();

        // create the basic model
        SoftwareSystem financialRiskSystem = model.addSoftwareSystem(Location.Internal, "Financial Risk System", "Calculates the bank's exposure to risk for product X");

        Person businessUser = model.addPerson(Location.Internal, "Business User", "A regular business user");
        businessUser.uses(financialRiskSystem, "Views reports");

        Person configurationUser = model.addPerson(Location.Internal, "Configuration User", "A regular business user who can also configure the parameters used in the risk calculations");
        configurationUser.uses(financialRiskSystem, "Configures parameters");

        SoftwareSystem tradeDataSystem = model.addSoftwareSystem(Location.Internal, "Trade Data System", "The system of record for trades of type X");
        financialRiskSystem.uses(tradeDataSystem, "Gets trade data from");

        SoftwareSystem referenceDataSystem = model.addSoftwareSystem(Location.Internal, "Reference Data System", "Manages reference data for all counterparties the bank interacts with");
        financialRiskSystem.uses(referenceDataSystem, "Gets counterparty data from");

        SoftwareSystem emailSystem = model.addSoftwareSystem(Location.Internal, "E-mail system", "Microsoft Exchange");
        financialRiskSystem.uses(emailSystem, "Sends a notification that a report is ready via e-mail to");
        emailSystem.delivers(businessUser, "Sends a notification that a report is ready via e-mail to");

        SoftwareSystem centralMonitoringService = model.addSoftwareSystem(Location.Internal, "Central Monitoring Service", "The bank-wide monitoring and alerting dashboard");
        financialRiskSystem.uses(centralMonitoringService, "Sends critical failure alerts to");

        SoftwareSystem activeDirectory = model.addSoftwareSystem(Location.Internal, "Active Directory", "Manages users and security roles across the bank");
        financialRiskSystem.uses(activeDirectory, "Uses for authentication and authorisation");

        // create some views
        ViewSet viewSet = workspace.getViews();
        SystemContextView contextView = viewSet.createContextView(financialRiskSystem);
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        // output the model as JSON
        JsonWriter jsonWriter = new JsonWriter(true);
        StringWriter stringWriter = new StringWriter();
        jsonWriter.write(workspace, stringWriter);
        System.out.println(stringWriter.toString());

        // and if you want to upload it to structurizr.com
        StructurizrClient structurizrClient = new StructurizrClient("https://api.structurizr.com", "key", "secret");
        workspace.setId(1234);
        structurizrClient.putWorkspace(workspace);
    }

}
