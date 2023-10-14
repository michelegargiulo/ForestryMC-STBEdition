package forestry.arboriculture.genetics.alleles;

import javax.annotation.Nullable;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

import forestry.api.arboriculture.EnumTreeChromosome;
import forestry.api.arboriculture.IAlleleFruit;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.arboriculture.FruitProviderNone;
import forestry.arboriculture.FruitProviderPod;
import forestry.arboriculture.FruitProviderPod.EnumPodType;
import forestry.arboriculture.FruitProviderRipening;
import forestry.core.items.ItemFruit.EnumFruit;

import static forestry.api.arboriculture.EnumFruitFamily.JUNGLE;
import static forestry.api.arboriculture.EnumFruitFamily.NONE;
import static forestry.api.arboriculture.EnumFruitFamily.NUX;
import static forestry.api.arboriculture.EnumFruitFamily.POMES;
import static forestry.api.arboriculture.EnumFruitFamily.PRUNES;

public class AlleleFruits {
	public static final IAlleleFruit fruitNone;
	public static final IAlleleFruit fruitApple;
	public static final IAlleleFruit fruitCocoa;
	public static final IAlleleFruit fruitChestnut;
	public static final IAlleleFruit fruitWalnut;
	public static final IAlleleFruit fruitCherry;
	public static final IAlleleFruit fruitDates;
	public static final IAlleleFruit fruitPapaya;
	public static final IAlleleFruit fruitLemon;
	public static final IAlleleFruit fruitPlum;

	public static final IAlleleFruit fruitAlmond;
	public static final IAlleleFruit fruitApricot;
	public static final IAlleleFruit fruitAvocado;
	public static final IAlleleFruit fruitBanana;
	public static final IAlleleFruit fruitBreadfruit;
	public static final IAlleleFruit fruitCashew;
	public static final IAlleleFruit fruitCedar;
	public static final IAlleleFruit fruitDragonfruit;
	public static final IAlleleFruit fruitDurian;
	public static final IAlleleFruit fruitFig;
	public static final IAlleleFruit fruitGooseberry;
	public static final IAlleleFruit fruitGrapefruit;
	public static final IAlleleFruit fruitGuava;
	public static final IAlleleFruit fruitHazelnut;
	public static final IAlleleFruit fruitJackfruit;
	public static final IAlleleFruit fruitJujuba;
	public static final IAlleleFruit fruitKiwi;
	public static final IAlleleFruit fruitLime;
	public static final IAlleleFruit fruitLychee;
	public static final IAlleleFruit fruitMango;
	public static final IAlleleFruit fruitNutmeg;
	public static final IAlleleFruit fruitOlive;
	public static final IAlleleFruit fruitOrange;
	public static final IAlleleFruit fruitPassionFruit;
	public static final IAlleleFruit fruitPawpaw;
	public static final IAlleleFruit fruitPeach;
	public static final IAlleleFruit fruitPear;
	public static final IAlleleFruit fruitPecan;
	public static final IAlleleFruit fruitPeppercorn;
	public static final IAlleleFruit fruitPersimmon;
	public static final IAlleleFruit fruitPistachio;
	public static final IAlleleFruit fruitPomegranate;
	public static final IAlleleFruit fruitRambutan;
	public static final IAlleleFruit fruitSoursop;
	public static final IAlleleFruit fruitStarfruit;
	public static final IAlleleFruit fruitTamarind;
	public static final IAlleleFruit fruitVanilla;

	private static final List<IAlleleFruit> fruitAlleles;
	@Nullable
	private static List<IAlleleFruit> fruitAllelesWithModels;

	static {
		ItemStack cocoaBean = new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getDyeDamage());

		fruitAlleles = Arrays.asList(
			fruitNone = new AlleleFruit("none", new FruitProviderNone("for.fruits.none", NONE)),
			fruitApple = new AlleleFruit("apple", new FruitProviderRipening("for.fruits.apple", POMES, new ItemStack(Items.APPLE), 1.0f)
				.setColours(new Color(0xff2e2e), new Color(0xE3F49C))
				.setOverlay("pomes")),
			fruitCocoa = new AlleleFruit("cocoa", new FruitProviderPod("for.fruits.cocoa", JUNGLE, EnumPodType.COCOA, cocoaBean)),
			// .setColours(0xecdca5, 0xc4d24a), true)
			fruitChestnut = new AlleleFruit("chestnut", new FruitProviderRipening("for.fruits.chestnut", NUX, EnumFruit.CHESTNUT.getStack(), 1.0f)
				.setRipeningPeriod(6)
				.setColours(new Color(0x7f333d), new Color(0xc4d24a))
				.setOverlay("nuts"), true),
			fruitWalnut = new AlleleFruit("walnut", new FruitProviderRipening("for.fruits.walnut", NUX, EnumFruit.WALNUT.getStack(), 1.0f)
				.setRipeningPeriod(8)
				.setColours(new Color(0xfba248), new Color(0xc4d24a))
				.setOverlay("nuts"), true),
			fruitCherry = new AlleleFruit("cherry", new FruitProviderRipening("for.fruits.cherry", PRUNES, EnumFruit.CHERRY.getStack(), 1.0f)
				.setColours(new Color(0xff2e2e), new Color(0xc4d24a))
				.setOverlay("berries"), true),
			fruitDates = new AlleleFruit("dates", new FruitProviderPod("for.fruits.dates", JUNGLE, EnumPodType.DATES, EnumFruit.DATES.getStack(4))),
			fruitPapaya = new AlleleFruit("papaya", new FruitProviderPod("for.fruits.papaya", JUNGLE, EnumPodType.PAPAYA, EnumFruit.PAPAYA.getStack())),
			fruitLemon = new AlleleFruit("lemon", new FruitProviderRipening("for.fruits.lemon", PRUNES, EnumFruit.LEMON.getStack(), 1.0f)
				.setColours(new Color(0xeeee00), new Color(0x99ff00))
				.setOverlay("citrus"), true),
			fruitPlum = new AlleleFruit("plum", new FruitProviderRipening("for.fruits.plum", PRUNES, EnumFruit.PLUM.getStack(), 1.0f)
				.setColours(new Color(0x663446), new Color(0xeeff1a))
				.setOverlay("plums"), true),

			// New fruits
			fruitAlmond = new AlleleFruit("almond", new FruitProviderRipening("for.fruits.almond", NUX, EnumFruit.ALMOND.getStack(), 1.0f)
					.setRipeningPeriod(8)
					.setColours(new Color(0x613b27), new Color(0x663e29))
					.setOverlay("nuts"), true),
			fruitApricot = new AlleleFruit("almond", new FruitProviderRipening("for.fruits.apricot", PRUNES, EnumFruit.APRICOT.getStack(), 1.0f)
					.setRipeningPeriod(8)
					.setColours(new Color(0xc98a24), new Color(0xd6a14b))
					.setOverlay("plums"), true),
			fruitAvocado = new AlleleFruit("avocado", new FruitProviderRipening("for.fruits.avocado", PRUNES, EnumFruit.AVOCADO.getStack(), 1.0f)
					.setRipeningPeriod(6)
					.setColours(new Color(0x52452f), new Color(0x364a2c))
					.setOverlay("plums"), true),
			fruitBanana = new AlleleFruit("banana", new FruitProviderPod("for.fruits.banana", JUNGLE, EnumPodType.BANANA, EnumFruit.BANANA.getStack())),
			fruitBreadfruit = new AlleleFruit("breadfruit", new FruitProviderRipening("for.fruits.breadfruit", PRUNES, EnumFruit.BREADFRUIT.getStack(), 1.0f)
					.setColours(new Color(0x51a12a), new Color(0x4d8233))
					.setOverlay("plums"), true),
			fruitCashew = new AlleleFruit("cashew", new FruitProviderRipening("for.fruits.cashew", NUX, EnumFruit.CASHEW.getStack(), 1.0f)
					.setColours(new Color(0xccc091), new Color(0xbac429))
					.setOverlay("citrus"), true),
			fruitCedar = new AlleleFruit("cedar", new FruitProviderRipening("for.fruits.cedar", PRUNES, EnumFruit.CEDAR.getStack(), 1.0f)
					.setColours(new Color(0xd2eb34), new Color(0xf5f53d))
					.setOverlay("citrus"), true),
			fruitDragonfruit = new AlleleFruit("dragonfruit", new FruitProviderRipening("for.fruits.dragonfruit", PRUNES, EnumFruit.DRAGONFRUIT.getStack(), 1.0f)
					.setColours(new Color(0xba275b), new Color(0x96d68b))
					.setOverlay("citrus"), true),
			fruitDurian = new AlleleFruit("durian", new FruitProviderRipening("for.fruits.durian", PRUNES, EnumFruit.DURIAN.getStack(), 1.0f)
					.setColours(new Color(0x7d6d36), new Color(0x948a68))
					.setOverlay("citrus"), true), // CHECK COLORS!
			fruitFig = new AlleleFruit("fig", new FruitProviderRipening("for.fruits.fig", PRUNES, EnumFruit.FIG.getStack(), 1.0f)
					.setColours(new Color(0x742ea6), new Color(0x6bcf61))
					.setOverlay("plums"), true),
			fruitGooseberry = new AlleleFruit("gooseberry", new FruitProviderRipening("for.fruits.gooseberry", PRUNES, EnumFruit.GOOSEBERRY.getStack(), 1.0f)
					.setColours(new Color(0xe3a509), new Color(0x56b04d))
					.setOverlay("berries"), true),
			fruitGrapefruit = new AlleleFruit("grapefruit", new FruitProviderRipening("for.fruits.grapefruit", PRUNES, EnumFruit.GRAPEFRUIT.getStack(), 1.0f)
					.setRipeningPeriod(6)
					.setColours(new Color(0xc97026), new Color(0xa7e35d))
					.setOverlay("plums"), true),
			fruitGuava = new AlleleFruit("guava", new FruitProviderRipening("for.fruits.guava", PRUNES, EnumFruit.GUAVA.getStack(), 1.0f)
					.setColours(new Color(0x91d63a), new Color(0xa2c971))
					.setOverlay("plums"), true),
			fruitHazelnut = new AlleleFruit("hazelnut", new FruitProviderRipening("for.fruits.hazelnut", NUX, EnumFruit.HAZELNUT.getStack(), 1.0f)
					.setColours(new Color(0x6b5409), new Color(0xa2de57))
					.setOverlay("berries"), true),
			fruitJackfruit = new AlleleFruit("jackfruit", new FruitProviderPod("for.fruits.jackfruit", JUNGLE, EnumPodType.JACKFRUIT, EnumFruit.JACKFRUIT.getStack(3))),
			fruitJujuba = new AlleleFruit("jujuba", new FruitProviderRipening("for.fruits.jujuba", PRUNES, EnumFruit.JUJUBA.getStack(), 1.0f)
					.setColours(new Color(0xcf4730), new Color(0x7fbf54))
					.setOverlay("berries"), true),
			fruitKiwi = new AlleleFruit("kiwi", new FruitProviderRipening("for.fruits.kiwi", PRUNES, EnumFruit.KIWI.getStack(), 1.0f)
					.setColours(new Color(0x635036), new Color(0x75ba50))
					.setOverlay("nuts"), true),
			fruitLime = new AlleleFruit("lime", new FruitProviderRipening("for.fruits.lime", PRUNES, EnumFruit.LIME.getStack(), 1.0f)
					.setColours(new Color(0x37d90f), new Color(0x9acf30))
					.setOverlay("citrus"), true),
			fruitLychee = new AlleleFruit("lychee", new FruitProviderRipening("for.fruits.lychee", PRUNES, EnumFruit.LYCHEE.getStack(), 1.0f)
					.setColours(new Color(0xd1265f), new Color(0xe3b6d5))
					.setOverlay("berries"), true),
			fruitMango = new AlleleFruit("mango", new FruitProviderRipening("for.fruits.mango", PRUNES, EnumFruit.MANGO.getStack(), 1.0f)
					.setColours(new Color(0xd1a63b), new Color(0x59d450))
					.setOverlay("plums"), true),
			fruitNutmeg = new AlleleFruit("nutmeg", new FruitProviderRipening("for.fruits.nutmeg", NUX, EnumFruit.NUTMEG.getStack(), 1.0f)
					.setColours(new Color(0xcc9a54), new Color(0x6ebf37))
					.setOverlay("nuts"), true),
			fruitOlive = new AlleleFruit("olive", new FruitProviderRipening("for.fruits.olive", PRUNES, EnumFruit.OLIVE.getStack(), 1.0f)
					.setColours(new Color(0x518215), new Color(0x9ad156))
					.setOverlay("berries"), true),
			fruitOrange = new AlleleFruit("orange", new FruitProviderRipening("for.fruits.orange", PRUNES, EnumFruit.ORANGE.getStack(), 1.0f)
					.setColours(new Color(0xf0a505), new Color(0x26801c))
					.setOverlay("citrus"), true),
			fruitPassionFruit = new AlleleFruit("passionfruit", new FruitProviderRipening("for.fruits.passionfruit", PRUNES, EnumFruit.PASSIONFRUIT.getStack(), 1.0f)
					.setColours(new Color(0x915f9c), new Color(0xa7bf7e))
					.setOverlay("plums"), true),
			fruitPawpaw = new AlleleFruit("pawpaw", new FruitProviderRipening("for.fruits.pawpaw", PRUNES, EnumFruit.PAWPAW.getStack(), 1.0f)
					.setColours(new Color(0x94c930), new Color(0x56a65d))
					.setOverlay("plums"), true),
			fruitPeach = new AlleleFruit("peach", new FruitProviderRipening("for.fruits.peach", PRUNES, EnumFruit.PEACH.getStack(), 1.0f)
					.setColours(new Color(0xffe5b4), new Color(0xa7d97e))
					.setOverlay("plums"), true),
			fruitPear = new AlleleFruit("pear", new FruitProviderRipening("for.fruits.pear", PRUNES, EnumFruit.PEAR.getStack(), 1.0f)
					.setColours(new Color(0x78e657), new Color(0xedb07b))
					.setOverlay("plums"), true),
			fruitPecan = new AlleleFruit("pecan", new FruitProviderRipening("for.fruits.pecan", NUX, EnumFruit.PECAN.getStack(), 1.0f)
					.setColours(new Color(0x8a6637), new Color(0xaad177))
					.setOverlay("nuts"), true),
			fruitPeppercorn = new AlleleFruit("peppercorn", new FruitProviderRipening("for.fruits.peppercorn", NUX, EnumFruit.PEPPERCORN.getStack(), 1.0f)
					.setColours(new Color(0X6dbf70), new Color(0x8aab65))
					.setOverlay("nuts"), true),
			fruitPersimmon = new AlleleFruit("persimmon", new FruitProviderRipening("for.fruits.persimmon", PRUNES, EnumFruit.PERSIMMON.getStack(), 1.0f)
					.setColours(new Color(0xe69c27), new Color(0x9bd966))
					.setOverlay("plums"), true),
			fruitPistachio = new AlleleFruit("pistachio", new FruitProviderRipening("for.fruits.pistachio", NUX, EnumFruit.PISTACHIO.getStack(), 1.0f)
					.setColours(new Color(0xc94e2c), new Color(0x6bc42f))
					.setOverlay("nuts"), true),
			fruitPomegranate = new AlleleFruit("pomegranate", new FruitProviderRipening("for.fruits.pomegranate", PRUNES, EnumFruit.POMEGRANATE.getStack(), 1.0f)
					.setColours(new Color(0xcc0c53), new Color(0xd9b34c))
					.setOverlay("plums"), true),
			fruitRambutan = new AlleleFruit("rambutan", new FruitProviderRipening("for.fruits.rambutan", PRUNES, EnumFruit.RAMBUTAN.getStack(), 1.0f)
					.setColours(new Color(0xf2114d), new Color(0x1dcf17))
					.setOverlay("plums"), true),
			fruitSoursop = new AlleleFruit("soursop", new FruitProviderRipening("for.fruits.soursop", PRUNES, EnumFruit.SOURSOP.getStack(), 1.0f)
					.setColours(new Color(0x059914), new Color(0x47e657))
					.setOverlay("plums"), true),
			fruitStarfruit = new AlleleFruit("starfruit", new FruitProviderRipening("for.fruits.starfruit", PRUNES, EnumFruit.STARFRUIT.getStack(), 1.0f)
					.setColours(new Color(0xebc034), new Color(0x27ba16))
					.setOverlay("nuts"), true),
			fruitTamarind = new AlleleFruit("tamarind", new FruitProviderRipening("for.fruits.tamarind", NUX, EnumFruit.TAMARIND.getStack(), 1.0f)
					.setRipeningPeriod(30)
					.setColours(new Color(0x4d3a1a), new Color(0x96cc6c))
					.setOverlay("nuts"), true),
			fruitVanilla = new AlleleFruit("vanilla", new FruitProviderRipening("for.fruits.vanilla", NUX, EnumFruit.VANILLA.getStack(), 1.0f)
					.setColours(new Color(0xf3e5ab), new Color(0x7bb576))
					.setOverlay("nuts"), true)
		);


	}

	public static void registerAlleles() {
		for (IAlleleFruit fruitAllele : fruitAlleles) {
			AlleleManager.alleleRegistry.registerAllele(fruitAllele, EnumTreeChromosome.FRUITS);
		}
	}

	public static List<IAlleleFruit> getFruitAlleles() {
		return fruitAlleles;
	}

	public static List<IAlleleFruit> getFruitAllelesWithModels() {
		if (fruitAllelesWithModels == null) {
			fruitAllelesWithModels = new ArrayList<>();
			for (IAllele allele : AlleleManager.alleleRegistry.getRegisteredAlleles(EnumTreeChromosome.FRUITS)) {
				if (allele instanceof IAlleleFruit) {
					IAlleleFruit alleleFruit = (IAlleleFruit) allele;
					if (alleleFruit.getModelName() != null) {
						fruitAllelesWithModels.add(alleleFruit);
					}
				}
			}
		}
		return fruitAllelesWithModels;
	}
}
