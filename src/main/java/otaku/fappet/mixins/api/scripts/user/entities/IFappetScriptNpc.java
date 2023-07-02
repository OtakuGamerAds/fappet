package otaku.fappet.mixins.api.scripts.user.entities;

import mchorse.mappet.api.scripts.code.mappet.triggers.MappetTrigger;

public interface IFappetScriptNpc
{

    /* Moved Methods from Mappet */

    /**
     * Sets NPC's tick trigger (Use this if you want to edit an existing `on tick trigger`).
     *
     * <pre>{@code
     *    c.getSubject().setOnTickTrigger("ScriptName", "FunctionName", 1, 0);
     * }</pre>
     *
     * @param scriptName Script name
     * @param functionName Function name
     * @param frequency Frequency
     * @param blockIndex Block number
     */
    public void setOnTickTrigger(String scriptName, String functionName, int frequency, int blockIndex);


    /**
     * Adds a new `on tick trigger` to the NPC.
     *
     * <pre>{@code
     *    c.getSubject().addOnTickTrigger("ScriptName", "FunctionName", 1);
     * }</pre>
     *
     * @param scriptName Script name
     * @param functionName Function name
     * @param frequency Frequency
     */
    public void addOnTickTrigger(String scriptName, String functionName, int frequency);

    /**
     * Removes all `on tick` triggers from the NPC.
     *
     * <pre>{@code
     *    c.getSubject().clearOnTickTriggers();
     * }</pre>
     */
    public void clearOnTickTriggers();

    /**
     * Sets NPC's on interaction trigger.
     *
     * <pre>{@code
     *    c.getSubject().setOnInteractTrigger("ScriptName", "FunctionName", 0);
     * }</pre>
     *
     * @param scriptName Script name
     * @param functionName Function name
     * @param blockIndex Block number
     */
    public void setOnInteractTrigger(String scriptName, String functionName, int blockIndex);

    /**
     * Adds NPC's on interaction trigger.
     *
     * <pre>{@code
     *    c.getSubject().addOnInteractTrigger("ScriptName", "FunctionName");
     * }</pre>
     *
     * @param scriptName Script name
     * @param functionName Function name
     */
    public void addOnInteractTrigger(String scriptName, String functionName);

    /**
     * Clears NPC's on interaction triggers.
     *
     * <pre>{@code
     *    c.getSubject().clearOnInteractTriggers();
     * }</pre>
     */
    public void clearOnInteractTriggers();

    /**
     * Adds a new NPC's patrol point with a script trigger.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @param scriptName Script name
     * @param functionName Function name
     */
    public void addPatrol(int x, int y, int z, String scriptName, String functionName);

    /**
     * Sets NPC's patrol point with a script trigger.
     *
     * <pre>{@code
     *    c.getSubject().setPatrol(x, y, z, "ScriptName", "FunctionName", 0);
     * }</pre>
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @param scriptName Script name
     * @param functionName Function name
     * @param patrolIndex Patrol index
     */
    public void setPatrol(int x, int y, int z, String scriptName, String functionName, int patrolIndex);
}
