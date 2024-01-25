package com.AweiMC.PalOptilGuard.SteamCmd;

public class PathValidator {

    public static boolean isEnglishPath(String path) {
        // 使用正则表达式检查路径是否只包含英文字符
        return path.matches("^[a-zA-Z:/\\\\]+$");
    }

    public static void main(String[] args) {
        // 示例用法
        String path = "C:/Users/User用户/Documents";  // 替换为你要检测的路径
        if (isEnglishPath(path)) {
            System.out.println("The path contains only English characters.");
        } else {
            System.out.println("The path contains non-English characters.");
        }
    }
}
