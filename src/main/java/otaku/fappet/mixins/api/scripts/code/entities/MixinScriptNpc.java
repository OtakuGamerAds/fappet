package otaku.fappet.mixins.api.scripts.code.entities;

import mchorse.mappet.api.npcs.NpcState;
import mchorse.mappet.api.scripts.code.entities.ScriptNpc;
import mchorse.mappet.api.scripts.user.entities.IScriptNpc;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;
import mchorse.mappet.api.triggers.blocks.ScriptTriggerBlock;
import mchorse.mappet.entities.EntityNpc;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ScriptNpc.class)
public abstract class MixinScriptNpc implements IScriptNpc
{
    protected EntityNpc entity = getMappetNpc();

    /* Moved Methods from Mappet */

    @Deprecated
    @Override
    public void setOnTickTrigger(String scriptName, String functionName, int frequency, int blockIndex)
    {
        NpcState state = this.entity.getState();
        AbstractTriggerBlock block = blockIndex < state.triggerTick.blocks.size() ? state.triggerTick.blocks.get(blockIndex) : null;

        if (block instanceof ScriptTriggerBlock)
        {
            ScriptTriggerBlock script = (ScriptTriggerBlock) block;

            script.string = scriptName;
            script.function = functionName;
            script.frequency = frequency;

            this.entity.setState(state, false);
        }
    }

    @Deprecated
    @Override
    public void addOnTickTrigger(String scriptName, String functionName, int frequency)
    {
        NpcState state = this.entity.getState();
        ScriptTriggerBlock block = new ScriptTriggerBlock();

        block.string = scriptName;
        block.function = functionName;
        block.frequency = frequency;

        state.triggerTick.blocks.add(block);
        this.entity.setState(state, false);
    }

    @Deprecated
    @Override
    public void clearOnTickTriggers()
    {
        NpcState state = this.entity.getState();

        state.triggerTick.blocks.clear();

        this.entity.setState(state, false);
    }

    @Deprecated
    @Override
    public void setOnInteractTrigger(String scriptName, String functionName, int blockIndex)
    {
        NpcState state = this.entity.getState();
        AbstractTriggerBlock block = blockIndex < state.triggerInteract.blocks.size() ? state.triggerInteract.blocks.get(blockIndex) : null;

        if (block instanceof ScriptTriggerBlock)
        {
            ScriptTriggerBlock script = (ScriptTriggerBlock) block;

            script.string = scriptName;
            script.function = functionName;

            this.entity.setState(state, false);
        }
    }

    @Deprecated
    @Override
    public void addOnInteractTrigger(String scriptName, String functionName)
    {
        NpcState state = this.entity.getState();
        ScriptTriggerBlock block = new ScriptTriggerBlock();

        block.string = scriptName;
        block.function = functionName;

        state.triggerInteract.blocks.add(block);
        this.entity.setState(state, false);
    }

    @Deprecated
    @Override
    public void clearOnInteractTriggers()
    {
        NpcState state = this.entity.getState();

        state.triggerInteract.blocks.clear();

        this.entity.setState(state, false);
    }

    @Deprecated
    @Override
    public void setPatrol(int x, int y, int z, String scriptName, String functionName, int patrolIndex)
    {
        NpcState state = this.entity.getState();

        if (patrolIndex >= 0)
        {
            if (patrolIndex < state.patrol.size())
            {
                state.patrol.set(patrolIndex, new BlockPos(x, y, z));
            }

            if (patrolIndex < state.patrolTriggers.size())
            {
                Trigger trigger = new Trigger();
                ScriptTriggerBlock block = new ScriptTriggerBlock();

                block.string = scriptName;
                block.function = functionName;

                trigger.blocks.add(block);
                state.patrolTriggers.set(patrolIndex, trigger);
            }
        }

        this.entity.setState(state, false);
    }

    @Deprecated
    @Override
    public void addPatrol(int x, int y, int z, String scriptName, String functionName)
    {
        NpcState state = this.entity.getState();

        state.follow = "";
        state.patrol.add(new BlockPos(x, y, z));

        Trigger trigger = new Trigger();
        ScriptTriggerBlock block = new ScriptTriggerBlock();

        block.string = scriptName;
        block.function = functionName;

        trigger.blocks.add(block);
        state.patrolTriggers.add(trigger);

        this.entity.setState(state, false);
    }
}
