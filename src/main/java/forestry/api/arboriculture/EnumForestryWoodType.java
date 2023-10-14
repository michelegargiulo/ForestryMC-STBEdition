/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.arboriculture;

import java.util.Locale;
import java.util.Random;

public enum EnumForestryWoodType implements IWoodType {
	LARCH,
	TEAK,
	ACACIA,
	SILVERLIME,
	CHESTNUT,
	WENGE,
	BAOBAB,
	SEQUOIA(4.0f),

	KAPOK,
	EBONY,
	MAHOGANY,
	BALSA(1.0f),
	WILLOW,
	WALNUT,
	GREENHEART(7.5f),
	CHERRY,

	MAHOE,
	POPLAR,
	PALM,
	PAPAYA,
	PINE(3.0f),
	PLUM,
	MAPLE,
	CITRUS,

	GIGANTEUM(4.0f),
	IPE,
	PADAUK,
	COCOBOLO,
	ZEBRAWOOD,


	// Harvestcraft
	ALMOND,
	APRICOT,
	AVOCADO,
	BANANA,
	BREADFRUIT,
	CASHEW,
	CEDAR,
	CINNAMON,
	DRAGONFRUIT,
	DURIAN,
	FIG,
	GOOSEBERRY,
	GRAPEFRUIT,
	GUAVA,
	HAZELNUT,
	JACKFRUIT,
	JUJUBA,
	KIWI,
	LIME,
	LYCHEE,
	MANGO,
	NUTMEG,
	OLIVE,
	ORANGE,
	PAPERBARK,
	PASSIONFRUIT,
	PAWPAW,
	PEACH,
	PEAR,
	PECAN,
	PEPPERCORN,
	PERSIMMON,
	PISTACHIO,
	POMEGRANATE,
	RAMBUTAN,
	SOURSOP,
	STARFRUIT,
	TAMARIND,
	VANILLA,
	YEW,

	/*
	// Extra Utilities

	// Immersive Intelligence
	RUBBER,

	// Integrated Dynamics
	MENRIL,

	// Natura
	SILVER_MAPLE,
	SILVERBELL,
	AMARANTH,
	TIGERWOOD,
	SALIX,
	SAKURA,
	EUCALYPTUS,
	HOPSEED,
	REDWOOD,

	BLOODWOOD(){

		@Override
		public String getPlankTexture() {
			if (Config.compatNaturaEnabled && ModUtil.isModLoaded("natura")) {
				return "natura:blocks/planks/nether/bloodwood_planks.png";
			} else return "forestry:blocks/wood/planks." + getName();
		}

		@Override
		public String getDoorLowerTexture() {
			if (Config.compatNaturaEnabled && ModUtil.isModLoaded("natura")) {
				return "natura:blocks/doors/bloodwood/bloodwood_door_bottom.png";
			} else return "forestry:blocks/doors/" + getName() + "_lower";
		}

		@Override
		public String getDoorUpperTexture() {
			if (Config.compatNaturaEnabled && ModUtil.isModLoaded("natura")) {
				return "natura:blocks/doors/bloodwood/bloodwood_door_top.png";
			} else return "forestry:blocks/doors/" + getName() + "_upper";
		}

		@Override
		public String getBarkTexture() {
			if (Config.compatNaturaEnabled && ModUtil.isModLoaded("natura")) {
				return "natura:blocks/logs/nether/bloodwood_bark.png";
			} else return "forestry:blocks/wood/bark." + getName();
		}

		@Override
		public String getHeartTexture() {
			if (Config.compatNaturaEnabled && ModUtil.isModLoaded("natura")) {
				return "natura:blocks/logs/nether/bloodwood_hearth.png";
			} else return "forestry:blocks/wood/heart." + getName();
		}
	},
	DARKWOOD,
	FUSEWOOD,
	GHOSTWOOD,

	// Oceanic Expanse
	COCONUT,

	// Quark
	SWAMP_OAK,
	BLOSSOMING,

	// RandomThings
	SPECTRE,

	// Thaumcraft
	GREATWOOD,
	SILVERWOOD,

	// Tinkers Construct
	GREEN_SLIME,
	PURPLE_SLIME,
	BLUE_SLIME,

	// Twilight Forest
	CANOPY,
	TWILIGHT_OAK,

	// Witchery
	ALDER,
	HAWTHORN,
	ROWAN
	*/
	;

	public static final float DEFAULT_HARDNESS = 2.0f;
	public static final EnumForestryWoodType[] VALUES = values();

	private final float hardness;

	EnumForestryWoodType() {
		this(DEFAULT_HARDNESS);
	}

	EnumForestryWoodType(float hardness) {
		this.hardness = hardness;
	}

	@Override
	public float getHardness() {
		return hardness;
	}

	public static EnumForestryWoodType getRandom(Random random) {
		return VALUES[random.nextInt(VALUES.length)];
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase(Locale.ENGLISH);
	}

	@Override
	public String getName() {
		return toString();
	}

	@Override
	public int getMetadata() {
		return ordinal();
	}

	public static EnumForestryWoodType byMetadata(int meta) {
		if (meta < 0 || meta >= VALUES.length) {
			meta = 0;
		}
		return VALUES[meta];
	}

	@Override
	public String getPlankTexture() {
		return "forestry:blocks/wood/planks." + getName();
	}

	@Override
	public String getDoorLowerTexture() {
		return "forestry:blocks/doors/" + getName() + "_lower";
	}

	@Override
	public String getDoorUpperTexture() {
		return "forestry:blocks/doors/" + getName() + "_upper";
	}

	@Override
	public String getBarkTexture() {
		return "forestry:blocks/wood/bark." + getName();
	}

	@Override
	public String getHeartTexture() {
		return "forestry:blocks/wood/heart." + getName();
	}
}
