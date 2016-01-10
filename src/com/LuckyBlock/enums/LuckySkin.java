package com.LuckyBlock.enums;

public enum LuckySkin {

    LUCKY("8bf094d3-3961-4c98-995a-dcfd33ce888b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3Jh"
            + "ZnQubmV0L3RleHR1cmUvYWVjOWU1YTRmNGQ2ZTIyMGQwNWNhMTlmNTQ5ZDkzNDkxZDU4OGIzZGIyNGI4NGZlN2VkNTU2NzZjMjJjMmE1In19fQ=="),
    PRESENT("cc5fdd1a-60f8-40d6-b667-a197e39f3bae", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW"
            + "5lY3JhZnQubmV0L3RleHR1cmUvZjBhZmE0ZmZmZDEwODYzZTc2YzY5OGRhMmM5YzllNzk5YmNmOWFiOWFhMzdkODMxMjg4MTczNDIyNWQzY2EifX19"),
    COLORED("e04609dd-57dc-4957-bfb9-424463c95c07", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5"
            + "lY3JhZnQubmV0L3RleHR1cmUvOGQ4ZTkzYWU4MjI1OTY3NWFmYjAxNjE0ODljYWVlNDE4YmJjNTBhMjcwNGNmYTEzODE2YWQ2ZGJkZDM4YmJhMCJ9fX0="),
    BLUE_PRESENT("c4f03c0b-0fd5-4472-adeb-b0833856e8be", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3"
            + "JhZnQubmV0L3RleHR1cmUvMWI2NzMwZGU3ZTViOTQxZWZjNmU4Y2JhZjU3NTVmOTQyMWEyMGRlODcxNzU5NjgyY2Q4ODhjYzRhODEyODIifX19"),;

    private String value;
    private String id;

    private LuckySkin(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

}
