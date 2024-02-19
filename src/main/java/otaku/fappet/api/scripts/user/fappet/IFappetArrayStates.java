package otaku.fappet.api.scripts.user.fappet;

import mchorse.mappet.api.scripts.user.mappet.IMappetStates;

import java.util.List;

/**
 * This class allows manipulation of array states within Minecraft scripts. Arrays are stored as JSON strings
 * in the game's state system and can be manipulated through this class's methods. It can be used with server,
 * players, and NPCs states, similar to how {@link IMappetStates} is used.
 *
 * <pre>{@code
 *    function main(c) {
 *        var states = c.getSubject().getStates();
 *        var nameArrayState = mappet.getArrayState(states, "array_state");
 *
 *        nameArrayState.push("element1");
 *        nameArrayState.push("element2");
 *        nameArrayState.push(3);
 *        print("Current array is: " + nameArrayState.get());
 *
 *        nameArrayState.remove("element1");
 *        print("element1 has been removed. Current Array is: " + nameArrayState.get());
 *
 *        print("Does array include element2? Answer: " + nameArrayState.includes("element2"));
 *
 *        print("Removing last element of the array ("+nameArrayState.pop()+").")
 *
 *        print("Current array is: " + nameArrayState.get());
 *
 *        nameArrayState.reset();
 *        print("Resetting array.");
 *        print("Current array is: " + nameArrayState.get());
 *    }
 * }</pre>
 */
public interface IFappetArrayStates {

    /**
     * Adds an item to the array state. If the array does not exist, it creates a new one.
     *
     * <pre>{@code
     *    var arrayState = mappet.getArrayState(states, "myArrayState");
     *    arrayState.push("newItem");
     * }</pre>
     */
    public void push(String item);

    /**
     * Removes and returns the last item of the array state. If the array is empty, this method returns null.
     *
     * <pre>{@code
     *    var arrayStates = mappet.getArrayState(states, "myArrayState");
     *    var lastItem = arrayStates.pop();
     *    if (lastItem != null) {
     *        c.send("Popped item: " + lastItem);
     *    } else {
     *        c.send("Array was empty.");
     *    }
     * }</pre>
     *
     * @return the last item of the array, or null if the array is empty
     */
    public String pop();

    /**
     * Removes an item from the array state. If the item does not exist in the array, the array remains unchanged.
     *
     * <pre>{@code
     *    var arrayState = mappet.getArrayState(states, "myArrayState");
     *    arrayState.remove("itemToRemove");
     * }</pre>
     */
    public void remove(String item);

    /**
     * Checks if an item is included in the array state.
     *
     * <pre>{@code
     *    var arrayState = mappet.getArrayState(states, "myArrayState");
     *    var includesItem = arrayState.includes("itemToCheck");
     *    c.send("Array includes item: " + includesItem);
     * }</pre>
     *
     * @return true if the array includes the item, false otherwise
     */
    public boolean includes(String item);

    /**
     * Retrieves the entire array state as a List of strings. If the array does not exist, it returns an empty List.
     *
     * <pre>{@code
     *    var arrayState = mappet.getArrayState(states, "myArrayState");
     *    var array = arrayState.get();
     *    c.send("Array content: " + array);
     * }</pre>
     *
     * @return the array state as a List of strings
     */
    public List<String> get();

    /**
     * Resets the array state to an empty array. If the array does not exist, this method creates an empty one.
     *
     * <pre>{@code
     *    var arrayState = mappet.getArrayState(states, "myArrayState");
     *    arrayState.reset();
     * }</pre>
     */
    public void reset();
}
