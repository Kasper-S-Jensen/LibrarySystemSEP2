package client.view.loanReserve;

import client.core.ModelFactoryClient;
import client.model.reservation.ReservationModelClient;
import javafx.beans.property.*;
import shared.materials.Material;
import shared.util.EventTypes;

import java.util.NoSuchElementException;

public class LoanReserveVM
{
    private StringProperty materialInfoProp;
    private IntegerProperty availNumberProp;
    private ObjectProperty<Material> materialProperty;
    private StringProperty warningProperty;
    private StringProperty reservationError;
    private ReservationModelClient reservationModelClient;

    public LoanReserveVM(ReservationModelClient reservationModelClient) {
        this.reservationModelClient = reservationModelClient;
        this.materialProperty = new SimpleObjectProperty<>();
        materialProperty.set(ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial());
        availNumberProp = new SimpleIntegerProperty(ModelFactoryClient.getInstance().getMaterialModelClient().numberOfAvailableCopies());
        materialInfoProp = new SimpleStringProperty(materialProperty.get().getMaterialDetails());
        warningProperty = new SimpleStringProperty();
        reservationError = new SimpleStringProperty();

        //Checks number of copies everytime a loan is made and end in the system.
        ModelFactoryClient.getInstance().getLoanModelClient().addPropertyChangeListener(
            EventTypes.LOANREGISTERED,(evt) -> availNumberProp.set(ModelFactoryClient.getInstance().getMaterialModelClient().numberOfAvailableCopies()) );

        ModelFactoryClient.getInstance().getLoanModelClient().addPropertyChangeListener(
            EventTypes.LOANENDED,(evt) -> availNumberProp.set(ModelFactoryClient.getInstance().getMaterialModelClient().numberOfAvailableCopies()) );
        //Listens for when a new material has been Selected and updates the information displayed.
        ModelFactoryClient.getInstance().getMaterialModelClient().addPropertyChangeListener("materialSelected", evt -> {
                materialProperty.set((Material) evt.getNewValue());
            materialInfoProp.set(materialProperty.get().getMaterialDetails());
            availNumberProp.set(ModelFactoryClient.getInstance().getMaterialModelClient().numberOfAvailableCopies());
            }
        );
    }


    public IntegerProperty getAvailNumberProp() {
        return availNumberProp;
    }

    public StringProperty getMaterialInfoProp() {
        return materialInfoProp;
    }

    public ObjectProperty<Material> materialProperty()
    {
        return materialProperty;
    }

    public void loanMaterial() {

        try
        {
            ModelFactoryClient.getInstance().getLoanModelClient().registerLoan(
                ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial(),
                ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());
            warningProperty.set("");
        }
        catch (IllegalStateException | NoSuchElementException e)
        {
            warningProperty.set(e.getMessage());
        }

    }

    public void reserveMaterial() {
        try
        {
            ModelFactoryClient.getInstance().getReservationModelClient().registerReservation(
                ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial(),
                ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());
            warningProperty.set("");

        }
        catch (IllegalStateException | NoSuchElementException e)
        {
            warningProperty.set(e.getMessage());
        }
    }



    public String getMaterialImageURL() {
        return materialProperty.get().getImageURL();
    }


    public StringProperty warningPropertyProperty()
    {
        return warningProperty;
    }
    public StringProperty reservationErrorProperty()
    {
        return reservationError;
    }

}
