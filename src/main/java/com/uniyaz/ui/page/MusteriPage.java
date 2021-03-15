package com.uniyaz.ui.page;


import com.uniyaz.core.domain.Musteri;
import com.uniyaz.core.service.MusteriService;
import com.uniyaz.ui.component.SySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

public class MusteriPage extends VerticalLayout {

    @PropertyId("id")
    private TextField id;

    @PropertyId("adi")
    private TextField adi;

    @PropertyId("telefon")
    private TextField telefon;

    private FormLayout mainLayout;

    private BeanItem<Musteri> MusteriBeanItem;
    private FieldGroup binder;
    private SySaveButton sySaveButton;

    public MusteriPage() {
        this(new Musteri());
    }

    public MusteriPage(Musteri musteri){
        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        MusteriBeanItem = new BeanItem<Musteri>(musteri);
        binder = new FieldGroup(MusteriBeanItem);
        binder.bindMemberFields(this);
    }

    private void buildMainLayout() {
        mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();

        id = new TextField();
        id.setCaption("ID");
        id.setEnabled(false);
        mainLayout.addComponent(id);

        adi = new TextField();
        adi.setCaption("Adı");
        mainLayout.addComponent(adi);

        telefon = new TextField();
        telefon.setCaption("Telefon");
        mainLayout.addComponent(telefon);

        sySaveButton = new SySaveButton();
        sySaveButton.addClickListener(new Button.ClickListener(){

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();

                    Musteri musteri = MusteriBeanItem.getBean();
                    MusteriService MusteriService = new MusteriService();
                    MusteriService.saveMusteri(musteri);
                } catch (FieldGroup.CommitException e) {
                    Notification.show("Alanlar nesne ile uyumlu değil", Notification.Type.ERROR_MESSAGE);
                } catch (Exception e) {
                    Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        mainLayout.addComponent(sySaveButton);
    }
}
