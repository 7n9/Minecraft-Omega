package net.minecraft.src;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class StatBase {
	public final int statId;
	public final String statName;
	public boolean isIndependent;
	public String statGuid;
	private final IStatType type;
	private static NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.US);
	public static IStatType simpleStatType = new StatTypeSimple();
	private static DecimalFormat decimalFormat = new DecimalFormat("########0.00");
	public static IStatType timeStatType = new StatTypeTime();
	public static IStatType distanceStatType = new StatTypeDistance();

	public StatBase(int i1, String string2, IStatType iStatType3) {
		this.isIndependent = false;
		this.statId = i1;
		this.statName = string2;
		this.type = iStatType3;
	}

	public StatBase(int i1, String string2) {
		this(i1, string2, simpleStatType);
	}

	public StatBase initIndependentStat() {
		this.isIndependent = true;
		return this;
	}

	public StatBase registerStat() {
		if(StatList.oneShotStats.containsKey(this.statId)) {
			throw new RuntimeException("Duplicate stat id: \"" + ((StatBase)StatList.oneShotStats.get(this.statId)).statName + "\" and \"" + this.statName + "\" at id " + this.statId);
		} else {
			StatList.field_25123_a.add(this);
			StatList.oneShotStats.put(this.statId, this);
			this.statGuid = AchievementMap.getGuid(this.statId);
			return this;
		}
	}

	public String toString() {
		return this.statName;
	}
}
