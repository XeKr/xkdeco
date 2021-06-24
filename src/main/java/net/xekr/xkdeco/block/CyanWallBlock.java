
package net.xekr.xkdeco.block;

import net.xekr.xkdeco.itemgroup.XkDecoOrientalItemGroup;
import net.xekr.xkdeco.XkdecoModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.state.Property;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.WallHeight;
import net.minecraft.block.WallBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

@XkdecoModElements.ModElement.Tag
public class CyanWallBlock extends XkdecoModElements.ModElement {
	@ObjectHolder("xkdeco:cyan_wall")
	public static final Block block = null;
	public CyanWallBlock(XkdecoModElements instance) {
		super(instance, 109);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(XkDecoOrientalItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class CustomBlock extends WallBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid().setOpaque((bs, br, bp) -> false));
			setRegistryName("cyan_wall");
		}
		private static final VoxelShape CENTER_POLE_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
		private static final VoxelShape WALL_CONNECTION_NORTH_SIDE_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 9.0D);
		private static final VoxelShape WALL_CONNECTION_SOUTH_SIDE_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 16.0D);
		private static final VoxelShape WALL_CONNECTION_WEST_SIDE_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
		private static final VoxelShape WALL_CONNECTION_EAST_SIDE_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
		private boolean shouldConnect(BlockState state, boolean checkattach, Direction face) {
			boolean flag = state.getBlock() instanceof WallBlock
					|| state.getBlock() instanceof FenceGateBlock && FenceGateBlock.isParallel(state, face);
			return !cannotAttach(state.getBlock()) && checkattach || flag;
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			IWorldReader iworldreader = context.getWorld();
			BlockPos blockpos = context.getPos();
			FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
			BlockPos blockpos1 = blockpos.north();
			BlockPos blockpos2 = blockpos.east();
			BlockPos blockpos3 = blockpos.south();
			BlockPos blockpos4 = blockpos.west();
			BlockPos blockpos5 = blockpos.up();
			BlockState blockstate = iworldreader.getBlockState(blockpos1);
			BlockState blockstate1 = iworldreader.getBlockState(blockpos2);
			BlockState blockstate2 = iworldreader.getBlockState(blockpos3);
			BlockState blockstate3 = iworldreader.getBlockState(blockpos4);
			BlockState blockstate4 = iworldreader.getBlockState(blockpos5);
			boolean flag = this.shouldConnect(blockstate, blockstate.isSolidSide(iworldreader, blockpos1, Direction.SOUTH), Direction.SOUTH);
			boolean flag1 = this.shouldConnect(blockstate1, blockstate1.isSolidSide(iworldreader, blockpos2, Direction.WEST), Direction.WEST);
			boolean flag2 = this.shouldConnect(blockstate2, blockstate2.isSolidSide(iworldreader, blockpos3, Direction.NORTH), Direction.NORTH);
			boolean flag3 = this.shouldConnect(blockstate3, blockstate3.isSolidSide(iworldreader, blockpos4, Direction.EAST), Direction.EAST);
			BlockState blockstate5 = this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
			return this.func_235626_a_(iworldreader, blockstate5, blockpos5, blockstate4, flag, flag1, flag2, flag3);
		}

		@Override /**
					 * Update the provided state given the provided neighbor facing and neighbor
					 * state, returning a new state. For example, fences make their connections to
					 * the passed in state if possible, and wet concrete powder immediately returns
					 * its solidified counterpart. Note that this method should ideally consider
					 * only the specific face passed in.
					 */
		public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos,
				BlockPos facingPos) {
			if (stateIn.get(WATERLOGGED)) {
				worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
			}
			if (facing == Direction.DOWN) {
				return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
			} else {
				return facing == Direction.UP
						? this.func_235625_a_(worldIn, stateIn, facingPos, facingState)
						: this.func_235627_a_(worldIn, currentPos, stateIn, facingPos, facingState, facing);
			}
		}

		private BlockState func_235625_a_(IWorldReader reader, BlockState state1, BlockPos pos, BlockState state2) {
			boolean flag = hasHeightForProperty(state1, WALL_HEIGHT_NORTH);
			boolean flag1 = hasHeightForProperty(state1, WALL_HEIGHT_EAST);
			boolean flag2 = hasHeightForProperty(state1, WALL_HEIGHT_SOUTH);
			boolean flag3 = hasHeightForProperty(state1, WALL_HEIGHT_WEST);
			return this.func_235626_a_(reader, state1, pos, state2, flag, flag1, flag2, flag3);
		}

		private BlockState func_235627_a_(IWorldReader reader, BlockPos p_235627_2_, BlockState p_235627_3_, BlockPos p_235627_4_,
				BlockState p_235627_5_, Direction directionIn) {
			Direction direction = directionIn.getOpposite();
			boolean flag = directionIn == Direction.NORTH
					? this.shouldConnect(p_235627_5_, p_235627_5_.isSolidSide(reader, p_235627_4_, direction), direction)
					: hasHeightForProperty(p_235627_3_, WALL_HEIGHT_NORTH);
			boolean flag1 = directionIn == Direction.EAST
					? this.shouldConnect(p_235627_5_, p_235627_5_.isSolidSide(reader, p_235627_4_, direction), direction)
					: hasHeightForProperty(p_235627_3_, WALL_HEIGHT_EAST);
			boolean flag2 = directionIn == Direction.SOUTH
					? this.shouldConnect(p_235627_5_, p_235627_5_.isSolidSide(reader, p_235627_4_, direction), direction)
					: hasHeightForProperty(p_235627_3_, WALL_HEIGHT_SOUTH);
			boolean flag3 = directionIn == Direction.WEST
					? this.shouldConnect(p_235627_5_, p_235627_5_.isSolidSide(reader, p_235627_4_, direction), direction)
					: hasHeightForProperty(p_235627_3_, WALL_HEIGHT_WEST);
			BlockPos blockpos = p_235627_2_.up();
			BlockState blockstate = reader.getBlockState(blockpos);
			return this.func_235626_a_(reader, p_235627_3_, blockpos, blockstate, flag, flag1, flag2, flag3);
		}

		private BlockState func_235626_a_(IWorldReader reader, BlockState state, BlockPos pos, BlockState collisionState, boolean connectedSouth,
				boolean connectedWest, boolean connectedNorth, boolean connectedEast) {
			VoxelShape voxelshape = collisionState.getCollisionShape(reader, pos).project(Direction.DOWN);
			BlockState blockstate = this.func_235630_a_(state, connectedSouth, connectedWest, connectedNorth, connectedEast, voxelshape);
			return blockstate.with(UP, Boolean.valueOf(this.func_235628_a_(blockstate, collisionState, voxelshape)));
		}

		private BlockState func_235630_a_(BlockState state, boolean connectedSouth, boolean connectedWest, boolean connectedNorth,
				boolean connectedEast, VoxelShape shape) {
			return state.with(WALL_HEIGHT_NORTH, this.func_235633_a_(connectedSouth, shape, WALL_CONNECTION_NORTH_SIDE_SHAPE))
					.with(WALL_HEIGHT_EAST, this.func_235633_a_(connectedWest, shape, WALL_CONNECTION_EAST_SIDE_SHAPE))
					.with(WALL_HEIGHT_SOUTH, this.func_235633_a_(connectedNorth, shape, WALL_CONNECTION_SOUTH_SIDE_SHAPE))
					.with(WALL_HEIGHT_WEST, this.func_235633_a_(connectedEast, shape, WALL_CONNECTION_WEST_SIDE_SHAPE));
		}

		private WallHeight func_235633_a_(boolean p_235633_1_, VoxelShape p_235633_2_, VoxelShape p_235633_3_) {
			if (p_235633_1_) {
				return compareShapes(p_235633_2_, p_235633_3_) ? WallHeight.TALL : WallHeight.LOW;
			} else {
				return WallHeight.NONE;
			}
		}

		private boolean func_235628_a_(BlockState p_235628_1_, BlockState p_235628_2_, VoxelShape shape) {
			boolean flag = p_235628_2_.getBlock() instanceof WallBlock && p_235628_2_.get(UP);
			if (flag) {
				return true;
			} else {
				WallHeight wallheight = p_235628_1_.get(WALL_HEIGHT_NORTH);
				WallHeight wallheight1 = p_235628_1_.get(WALL_HEIGHT_SOUTH);
				WallHeight wallheight2 = p_235628_1_.get(WALL_HEIGHT_EAST);
				WallHeight wallheight3 = p_235628_1_.get(WALL_HEIGHT_WEST);
				boolean flag1 = wallheight1 == WallHeight.NONE;
				boolean flag2 = wallheight3 == WallHeight.NONE;
				boolean flag3 = wallheight2 == WallHeight.NONE;
				boolean flag4 = wallheight == WallHeight.NONE;
				boolean flag5 = flag4 && flag1 && flag2 && flag3 || flag4 != flag1 || flag2 != flag3;
				if (flag5) {
					return true;
				} else {
					boolean flag6 = wallheight == WallHeight.TALL && wallheight1 == WallHeight.TALL
							|| wallheight2 == WallHeight.TALL && wallheight3 == WallHeight.TALL;
					if (flag6) {
						return false;
					} else {
						return p_235628_2_.getBlock().isIn(BlockTags.WALL_POST_OVERRIDE) || compareShapes(shape, CENTER_POLE_SHAPE);
					}
				}
			}
		}

		private static boolean hasHeightForProperty(BlockState state, Property<WallHeight> heightProperty) {
			return state.get(heightProperty) != WallHeight.NONE;
		}

		private static boolean compareShapes(VoxelShape shape1, VoxelShape shape2) {
			return !VoxelShapes.compare(shape2, shape1, IBooleanFunction.ONLY_FIRST);
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.CYAN;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
