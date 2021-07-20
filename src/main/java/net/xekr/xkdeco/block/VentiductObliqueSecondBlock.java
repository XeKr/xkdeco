
package net.xekr.xkdeco.block;

import net.xekr.xkdeco.itemgroup.XkDecoPunkItemGroup;
import net.xekr.xkdeco.XkdecoModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IWorld;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.StateContainer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.BooleanProperty;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

@XkdecoModElements.ModElement.Tag
public class VentiductObliqueSecondBlock extends XkdecoModElements.ModElement {
	@ObjectHolder("xkdeco:ventiduct_oblique_second")
	public static final Block block = null;
	public VentiductObliqueSecondBlock(XkdecoModElements instance) {
		super(instance, 751);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(XkDecoPunkItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class CustomBlock extends Block implements IWaterLoggable {
		public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
		public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid().setOpaque((bs, br, bp) -> false));
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
			setRegistryName("ventiduct_oblique_second");
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			Vector3d offset = state.getOffset(world, pos);
			switch ((Direction) state.get(FACING)) {
				case SOUTH :
				default :
					return VoxelShapes
							.combineAndSimplify(
									VoxelShapes.or(makeCuboidShape(16, 0, 4, 0, 16, 0), makeCuboidShape(16, -2, 6, 0, 16, 4),
											makeCuboidShape(16, -4, 8, 0, 16, 6), makeCuboidShape(16, -6, 10, 0, 16, 8),
											makeCuboidShape(16, -8, 12, 0, 16, 10), makeCuboidShape(16, -10, 14, 0, 14, 12),
											makeCuboidShape(16, -12, 16, 0, 12, 14)),
									VoxelShapes.or(makeCuboidShape(14, 2, 4, 2, 14, 0), makeCuboidShape(14, 0, 6, 2, 14, 4),
											makeCuboidShape(14, -2, 8, 2, 14, 6), makeCuboidShape(14, -4, 10, 2, 14, 8),
											makeCuboidShape(14, -6, 12, 2, 14, 10), makeCuboidShape(14, -8, 14, 2, 12, 12),
											makeCuboidShape(14, -10, 16, 2, 10, 14)),
									IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
				case NORTH :
					return VoxelShapes.combineAndSimplify(
							VoxelShapes.or(makeCuboidShape(0, 0, 12, 16, 16, 16), makeCuboidShape(0, -2, 10, 16, 16, 12),
									makeCuboidShape(0, -4, 8, 16, 16, 10), makeCuboidShape(0, -6, 6, 16, 16, 8), makeCuboidShape(0, -8, 4, 16, 16, 6),
									makeCuboidShape(0, -10, 2, 16, 14, 4), makeCuboidShape(0, -12, 0, 16, 12, 2)),
							VoxelShapes.or(makeCuboidShape(2, 2, 12, 14, 14, 16), makeCuboidShape(2, 0, 10, 14, 14, 12),
									makeCuboidShape(2, -2, 8, 14, 14, 10), makeCuboidShape(2, -4, 6, 14, 14, 8), makeCuboidShape(2, -6, 4, 14, 14, 6),
									makeCuboidShape(2, -8, 2, 14, 12, 4), makeCuboidShape(2, -10, 0, 14, 10, 2)),
							IBooleanFunction.ONLY_FIRST).withOffset(offset.x, offset.y, offset.z);
				case EAST :
					return VoxelShapes
							.combineAndSimplify(
									VoxelShapes.or(makeCuboidShape(4, 0, 0, 0, 16, 16), makeCuboidShape(6, -2, 0, 4, 16, 16),
											makeCuboidShape(8, -4, 0, 6, 16, 16), makeCuboidShape(10, -6, 0, 8, 16, 16),
											makeCuboidShape(12, -8, 0, 10, 16, 16), makeCuboidShape(14, -10, 0, 12, 14, 16),
											makeCuboidShape(16, -12, 0, 14, 12, 16)),
									VoxelShapes.or(makeCuboidShape(4, 2, 2, 0, 14, 14), makeCuboidShape(6, 0, 2, 4, 14, 14),
											makeCuboidShape(8, -2, 2, 6, 14, 14), makeCuboidShape(10, -4, 2, 8, 14, 14),
											makeCuboidShape(12, -6, 2, 10, 14, 14), makeCuboidShape(14, -8, 2, 12, 12, 14),
											makeCuboidShape(16, -10, 2, 14, 10, 14)),
									IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
				case WEST :
					return VoxelShapes.combineAndSimplify(
							VoxelShapes.or(makeCuboidShape(12, 0, 16, 16, 16, 0), makeCuboidShape(10, -2, 16, 12, 16, 0),
									makeCuboidShape(8, -4, 16, 10, 16, 0), makeCuboidShape(6, -6, 16, 8, 16, 0), makeCuboidShape(4, -8, 16, 6, 16, 0),
									makeCuboidShape(2, -10, 16, 4, 14, 0), makeCuboidShape(0, -12, 16, 2, 12, 0)),
							VoxelShapes.or(makeCuboidShape(12, 2, 14, 16, 14, 2), makeCuboidShape(10, 0, 14, 12, 14, 2),
									makeCuboidShape(8, -2, 14, 10, 14, 2), makeCuboidShape(6, -4, 14, 8, 14, 2), makeCuboidShape(4, -6, 14, 6, 14, 2),
									makeCuboidShape(2, -8, 14, 4, 12, 2), makeCuboidShape(0, -10, 14, 2, 10, 2)),
							IBooleanFunction.ONLY_FIRST).withOffset(offset.x, offset.y, offset.z);
			}
		}

		@Override
		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(FACING, WATERLOGGED);
		}

		public BlockState rotate(BlockState state, Rotation rot) {
			return state.with(FACING, rot.rotate(state.get(FACING)));
		}

		public BlockState mirror(BlockState state, Mirror mirrorIn) {
			return state.rotate(mirrorIn.toRotation(state.get(FACING)));
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			boolean flag = context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER;;
			return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, flag);
		}

		@Override
		public FluidState getFluidState(BlockState state) {
			return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
		}

		@Override
		public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos,
				BlockPos facingPos) {
			if (state.get(WATERLOGGED)) {
				world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
			}
			return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
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
