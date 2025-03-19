class SplitStrings {
    public static void main(String[] args)
    {
        //input validation
        if(args.length != 1)
        {
            System.out.println("Invalid number of arguments");
            System.out.println("Usage: java SplitStrings <string>");
            System.out.println("Example: java SplitStrings 192.168.1.1");
            System.out.println("Example Output:");
            System.out.println("192");
            System.out.println("168");
            System.out.println("1");
            System.out.println("1");
            return;
        }
        //else part in case of valid input
        int start = 0;
        int dotPosition;
        while((dotPosition = args[0].indexOf(".", start)) != -1)
        {
            if(args[0].substring(start, dotPosition).equals(""))
            {
                start = dotPosition + 1;
                continue;
            }
            System.out.println(args[0].substring(start, dotPosition));
            start = dotPosition + 1;
        }
        System.out.println(args[0].substring(start));

        // String[] parts = args[0].split("\\.");
        // String[] parts = args[0].split("[.]");
        // for(String part : parts)
        // {
        //     if(part.equals(""))
        //         continue;
        //     System.out.println(part);
        // }
    }
}