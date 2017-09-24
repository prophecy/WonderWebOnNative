package WonderUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ProphecyX on 8/30/2017 AD.
 */

public class WonderEvent {

    public interface EventListener {
        void onEvent(String evt);
    }

    private Set<EventListener> eventListenerSet;

    public WonderEvent() {

        eventListenerSet = new HashSet<>();
    }

    public void dispatch(String evt) {

        for (EventListener eventListener : eventListenerSet)
            eventListener.onEvent(evt);
    }

    public void register(EventListener eventListener) {

        eventListenerSet.add(eventListener);
    }

    public void deRegister(EventListener eventListener) {

        eventListenerSet.remove(eventListener);
    }

    public void emptyEventListener() {

        eventListenerSet.clear();
    }
}
