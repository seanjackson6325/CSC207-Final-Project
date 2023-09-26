public class APItest {
    private String token = "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";


    // STILL TRYING TO FIGURE IT OUT DOESN't WORK
    String[] cmd = new String[]{"curl -X POST https://postman-echo.com/post --data foo1=bar1&foo2=bar2"};
    Process process = Runtime.getRuntime().exec(cmd);
    process.getInputStream();
    process.destroy();
}
