package otaku.fappet.api.scripts.code.fappet;

import mchorse.mappet.api.scripts.user.mappet.IMappetStates;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FappetArrayStates
{

    private IMappetStates states;
    private String stateName;
    private Gson gson = new Gson();

    public FappetArrayStates(IMappetStates states, String stateName)
    {
        this.states = states;
        this.stateName = stateName;
    }

    public void push(String item)
    {
        List<String> list = getOrCreateArrayState();
        list.add(item);
        saveState(list);
    }

    public void remove(String item)
    {
        List<String> list = getOrCreateArrayState();
        list.remove(item);
        saveState(list);
    }

    public String pop()
    {
        List<String> list = getOrCreateArrayState();
        if (list.isEmpty())
        {
            return null;
        }
        else
        {
            String item = list.remove(list.size() - 1);
            saveState(list);
            return item;
        }
    }

    public boolean includes(String item)
    {
        List<String> list = getOrCreateArrayState();
        return list.contains(item);
    }

    public List<String> get()
    {
        return getOrCreateArrayState();
    }

    public void reset()
    {
        saveState(new ArrayList<>());
    }

    private List<String> getOrCreateArrayState()
    {
        String stateValue = states.getString(this.stateName);
        if (stateValue == null || stateValue.isEmpty())
        {
            return new ArrayList<>();
        }
        else
        {
            Type listType = new TypeToken<ArrayList<String>>(){}.getType();
            return gson.fromJson(stateValue, listType);
        }
    }

    private void saveState(List<String> list)
    {
        states.setString(this.stateName, gson.toJson(list));
    }
}
