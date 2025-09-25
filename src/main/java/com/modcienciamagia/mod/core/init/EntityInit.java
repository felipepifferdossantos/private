package com.modcienciamagia.mod.core.init;

import com.modcienciamagia.mod.ModCienciaMagia;
import com.modcienciamagia.mod.core.entities.boss.RedWitherEntity;
import com.modcienciamagia.mod.core.entities.minions.RedWitherlingEntity;
import com.modcienciamagia.mod.core.entities.projectiles.CorruptedSkullEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModCienciaMagia.MODID);

    public static final RegistryObject<EntityType<RedWitherEntity>> RED_WITHER =
            ENTITIES.register("red_wither",
                    () -> EntityType.Builder.of(RedWitherEntity::new, MobCategory.MONSTER)
                            .sized(0.9F, 3.5F)
                            .fireImmune()
                            .build("red_wither"));

    public static final RegistryObject<EntityType<CorruptedSkullEntity>> CORRUPTED_SKULL =
            ENTITIES.register("corrupted_skull",
                    () -> EntityType.Builder.<CorruptedSkullEntity>of(CorruptedSkullEntity::new, MobCategory.MISC)
                            .sized(0.3125F, 0.3125F)
                            .build("corrupted_skull"));

    public static final RegistryObject<EntityType<RedWitherlingEntity>> RED_WITHERLING =
            ENTITIES.register("red_witherling",
                    () -> EntityType.Builder.of(RedWitherlingEntity::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.5F)
                            .build("red_witherling"));
}