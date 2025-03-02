class Calculator{
    public static void main(String[] args){
        if(args.length != 3){
            System.out.println("Invalid number of arguments");
            System.out.println("Usage: java Calculator <number1> <operation> <number2>");
            System.out.println("Operations: +, -, x, /");
            System.out.println("Example: java Calculator 5 + 3");
            return;
        }
        switch(args[1]){
            case "+":
                System.out.println(Integer.parseInt(args[0]) + Integer.parseInt(args[2]));
                break;
            case "-":
                System.out.println(Integer.parseInt(args[0]) - Integer.parseInt(args[2]));
                break;
            case "x":
                System.out.println(Integer.parseInt(args[0]) * Integer.parseInt(args[2]));
                break;
            case "/":
                System.out.println(Integer.parseInt(args[0]) / Integer.parseInt(args[2]));
                break;
            default:
                System.out.println("Invalid operation");
                System.out.println("Operations: +, -, x, /");
                break;
        }
    }
}