package net.gourmand.core.registry.worldgen;

import com.mojang.serialization.Codec;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.neoforged.neoforge.common.MonsterRoomHooks;

import java.util.function.Predicate;

public class ModpackMonsterRoom extends MonsterRoomFeature {

    public ModpackMonsterRoom(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        Predicate<BlockState> predicate = Feature.isReplaceable(BlockTags.FEATURES_CANNOT_REPLACE);
        BlockPos blockpos = context.origin();
        RandomSource randomsource = context.random();
        WorldGenLevel worldgenlevel = context.level();
        Rock rock = randomRockType(randomsource);

        int r = randomsource.nextInt(2) + 2;
        int rx1 = -r - 1;
        int rx2 = r + 1;
        int r2 = randomsource.nextInt(2) + 2;
        int rz1 = -r2 - 1;
        int rz2 = r2 + 1;
        int c = 0;

        for(int x = rx1; x <= rx2; ++x) {
            for(int y = -1; y <= 4; ++y) {
                for(int z = rz1; z <= rz2; ++z) {
                    BlockPos pos = blockpos.offset(x, y, z);
                    boolean isSolid = worldgenlevel.getBlockState(pos).isSolid();
                    if (y == -1 && !isSolid) {
                        return false;
                    }

                    if (y == 4 && !isSolid) {
                        return false;
                    }

                    if ((x == rx1 || x == rx2 || z == rz1 || z == rz2) && y == 0 && worldgenlevel.isEmptyBlock(pos) && worldgenlevel.isEmptyBlock(pos.above())) {
                        ++c;
                    }
                }
            }
        }

        if (c >= 1 && c <= 5) {
            for(int x = rx1; x <= rx2; ++x) {
                for(int y = 4; y >= -1; --y) {
                    for(int z = rz1; z <= rz2; ++z) {
                        BlockPos pos = blockpos.offset(x, y, z);
                        BlockState blockstate = worldgenlevel.getBlockState(pos);
                        if (x != rx1 && y != -1 && z != rz1 && x != rx2 && y != 4 && z != rz2) {
                            if (!blockstate.is(Blocks.BARREL) && !blockstate.is(Blocks.SPAWNER)) {
                                this.safeSetBlock(worldgenlevel, pos, Blocks.AIR.defaultBlockState(), predicate);
                            }
                        } else if (pos.getY() >= worldgenlevel.getMinBuildHeight() && !worldgenlevel.getBlockState(pos.below()).isSolid()) {
                            worldgenlevel.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                        } else if (blockstate.isSolid() && !blockstate.is(Blocks.BARREL)) {
                            if ((y == -1 || y == 4) && randomsource.nextInt(4) != 0) {
                                this.safeSetBlock(worldgenlevel, pos, getStructureBlock( true, rock), predicate);
                            } else {
                                this.safeSetBlock(worldgenlevel, pos, getStructureBlock( false, rock), predicate);
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < 2; ++i) {
                for(int j = 0; j < 3; ++j) {
                    int x = blockpos.getX() + randomsource.nextInt(r * 2 + 1) - r;
                    int y = blockpos.getY();
                    int z = blockpos.getZ() + randomsource.nextInt(r2 * 2 + 1) - r2;
                    BlockPos blockPos = new BlockPos(x, y, z);
                    if (worldgenlevel.isEmptyBlock(blockPos)) {
                        int count = 0;

                        for(Direction direction : Direction.Plane.HORIZONTAL) {
                            if (worldgenlevel.getBlockState(blockPos.relative(direction)).isSolid()) {
                                ++count;
                            }
                        }

                        if (count == 1) {
                            this.safeSetBlock(worldgenlevel, blockPos, Blocks.BARREL.defaultBlockState().setValue(BarrelBlock.FACING, Direction.UP), predicate);
                            RandomizableContainer.setBlockEntityLootTable(worldgenlevel, randomsource, blockPos, BuiltInLootTables.SIMPLE_DUNGEON);
                            break;
                        }
                    }
                }
            }

            this.safeSetBlock(worldgenlevel, blockpos, Blocks.SPAWNER.defaultBlockState(), predicate);
            BlockEntity spawner = worldgenlevel.getBlockEntity(blockpos);
            if (spawner instanceof SpawnerBlockEntity spawnerblockentity) {
                spawnerblockentity.setEntityId(randomEntityId(randomsource), randomsource);
            } else {
                AncientGroundCore.LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", blockpos.getX(), blockpos.getY(), blockpos.getZ());
            }

            return true;
        } else {
            return false;
        }
    }

    private EntityType<?> randomEntityId(RandomSource random) {
        return MonsterRoomHooks.getRandomMonsterRoomMob(random);
    }

    private BlockState getStructureBlock(boolean alt, Rock rock){

        if (alt){
            return TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.CRACKED_BRICKS).get().defaultBlockState();
        } else {
            return TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.BRICKS).get().defaultBlockState();
        }
    }

    private Rock randomRockType(RandomSource random) {
        int value = random.nextIntBetweenInclusive(0, Rock.values().length - 1);
        return Rock.values()[value];
    }
}
