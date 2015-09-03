package in.co.foodamigo.foodapp.view.app;

import common.module.common.view.app.AbstractDrawerActivity;
import common.module.drawer.controller.DrawerController;
import in.co.foodamigo.foodapp.view.controller.FoodAmigoDrawerController;

public abstract class HomeActivity extends AbstractDrawerActivity {

    @Override
    protected DrawerController getDrawerController() {
        return new FoodAmigoDrawerController();
    }


}
