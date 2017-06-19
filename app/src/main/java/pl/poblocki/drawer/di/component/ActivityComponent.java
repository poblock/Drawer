package pl.poblocki.drawer.di.component;

import dagger.Subcomponent;
import pl.poblocki.drawer.di.module.ActivityModule;
import pl.poblocki.drawer.di.scope.ActivityScope;
import pl.poblocki.drawer.view.AirportActivity;
import pl.poblocki.drawer.view.ButtonFragment;
import pl.poblocki.drawer.view.ItemPagerFragment;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(AirportActivity airportActivity);
    void inject(ButtonFragment buttonFragment);
    void inject(ItemPagerFragment itemPagerFragment);
}
