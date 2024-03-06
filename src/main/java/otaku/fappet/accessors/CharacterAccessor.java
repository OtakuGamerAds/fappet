package otaku.fappet.accessors;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.metamorph.api.morphs.AbstractMorph;

public interface CharacterAccessor {
    boolean setupTempHUD(
            String tempId,
            AbstractMorph morph,
            boolean shouldOrtho,
            ScriptVector ortho,
            int duration,
            float fov,
            boolean hideInF1,
            boolean global,
            ScriptVector translate,
            ScriptVector scale,
            ScriptVector rotate
    );
}
