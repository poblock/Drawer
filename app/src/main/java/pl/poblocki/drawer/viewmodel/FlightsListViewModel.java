package pl.poblocki.drawer.viewmodel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pl.poblocki.drawer.data.FlightManager;
import pl.poblocki.drawer.model.Flight;
import pl.poblocki.drawer.support.ListBinder;

/**
 * Created by krzysztof.poblocki on 2017-06-08.
 */

//@ActivityScope
public class FlightsListViewModel {
    private final ListBinder<FlightViewModel> listBinder;
    private final FlightManager flightManager;
    private final List<FlightViewModel> list = new ArrayList<>();
    private PublishSubject<Integer> scrollTo = PublishSubject.create();

    @Inject
    FlightsListViewModel(ListBinder<FlightViewModel> listBinder, FlightManager flightManager) {
        this.listBinder = listBinder;
        this.flightManager = flightManager;
    }

    public Observable<Integer> scrollTo() {
        return scrollTo.hide();
    }

    public ListBinder<FlightViewModel> getListBinder() {
        return listBinder;
    }

    public List<FlightViewModel> getList() {
        return list;
    }

    public void initialize() {
        list.addAll(toViewModels(flightManager.getFlights()));
        listBinder.notifyDataChange(list);
    }

//    void setCompleted(int position, boolean completed) {
//        TodoViewModel viewModel = todos.get(position);
//        if (viewModel.completed != completed) {
//            viewModel = viewModel.setCompleted(completed);
//            todoRepo.updateTodo(viewModel.toModel());
//            todos.set(position, viewModel);
//            todoListBinder.notifyDataChange(todos);
//        }
//    }

    void create(String title, String dueDate) {
//        Flight flight = flightManager.createFlight(title, dueDate);
//        FlightViewModel viewModel = new FlightViewModel(flight);
//        list.add(0, viewModel);
//        listBinder.notifyDataChange(list);
//        scrollTo.onNext(0);
    }

    void deleteTodo(int position) {
        FlightViewModel viewModel = list.get(position);
//        flightManager.deleteFlight(viewModel.toModel());
        list.remove(position);
        listBinder.notifyDataChange(list);
    }

    @SuppressWarnings("Convert2streamapi")
    private List<FlightViewModel> toViewModels(List<Flight> list) {
        List<FlightViewModel> viewModels = new ArrayList<>();
        for (Flight flight : list) {
            viewModels.add(new FlightViewModel(flight));
        }
        return viewModels;
    }
}
