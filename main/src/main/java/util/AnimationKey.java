package util;

import java.util.Objects;

public record AnimationKey(String preffix, String suffix, String alt) {

    public boolean isEmpty() {
        return this.preffix.isEmpty() && this.suffix.isEmpty() && this.alt.isEmpty();
    }

    @Override
    public String toString() {
        String r = preffix;
        if (!preffix.isEmpty() && !suffix.isEmpty()) r += "_";
        r += suffix;
        if (!suffix.isEmpty() && !alt.isEmpty()) r += "_" + alt;
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimationKey that = (AnimationKey) o;

        if (!Objects.equals(preffix, that.preffix)) return false;
        if (!Objects.equals(suffix, that.suffix)) return false;
        return Objects.equals(alt, that.alt);
    }

    @Override
    public int hashCode() {
        int result = preffix != null ? preffix.hashCode() : 0;
        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
        result = 31 * result + (alt != null ? alt.hashCode() : 0);
        return result;
    }
}
