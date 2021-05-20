package client.view.borrowreserve;

import client.core.ModelFactoryClient;
import client.model.material.MaterialModelClient;
import javafx.beans.property.*;
import shared.materials.Material;
import shared.util.EventTypes;

import java.util.NoSuchElementException;

public class BorrowReserveVM {
    private StringProperty materialInfoProp;
    private IntegerProperty availNumberProp;
    private ObjectProperty<Material> materialProperty;
    private StringProperty warningProperty;

    public BorrowReserveVM() {
        //TODO: Lav om til listener, så antallet af kopier opdateres dynamisk.
        this.materialProperty = new SimpleObjectProperty<>();
        materialProperty.set(ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial());
        availNumberProp = new SimpleIntegerProperty(ModelFactoryClient.getInstance().getMaterialModelClient().numberOfAvailableCopies());
        materialInfoProp = new SimpleStringProperty(materialProperty.get().toString());
        System.out.println(materialProperty.get().toString());
        warningProperty = new SimpleStringProperty();

        //Checks number of copies everytime a loan is made in the system.
        ModelFactoryClient.getInstance().getLoanModelClient().addPropertyChangeListener(
            EventTypes.LOANREGISTERED,(evt) -> availNumberProp.set(ModelFactoryClient.getInstance().getMaterialModelClient().numberOfAvailableCopies()) );
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

        ModelFactoryClient.getInstance().getReservationModelClient().registerReservation(
                ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial(),
                ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());
    }


    public String getMaterialImageURL() {
        return ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial().getImageURL();
    }


    public StringProperty warningPropertyProperty()
    {
        return warningProperty;
    }
}
