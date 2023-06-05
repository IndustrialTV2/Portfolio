import java.util.Scanner;

public class Case2 {
	
	

	public static void main(String[] args) {
		
		Case2 case2 = new Case2();
		case2.start();
		
	}
		
	private void start() {
		int caseNumber;

		Recipe[] recipeList;
		int recipeListIndex = 0;
		Scanner scanner = new Scanner(System.in);
		
		
		//Inputs for initial cases and ingredient, portion and desired portion numbers. Saved as variables for clarity.
		caseNumber = scanner.nextInt();
		recipeList = new Recipe[caseNumber];
	
		//Loop for each recipe
		for(int i = 1; i <= caseNumber; i++) {
			
			//Creates a new recipe with ingredient number, portion number and desired portion number
			Recipe newRecipe = new Recipe(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			
			//Creates a new ingredient for each ingredient number
			for(int a = 0; a < newRecipe.getIngredientListLength(); a++) {

				Ingredient newIngredient = new Ingredient(scanner.next(), scanner.nextFloat(), scanner.nextFloat());
				
				//Adds each ingredient and the main ingredient weight to the recipe
				newRecipe.addIngredient(newIngredient);

				if(newIngredient.getPercentage() == 100.0) {
					newRecipe.setMainIngredientWeight(newIngredient.getWeight());
				}
			}
			
			//Adds the completed recipe to the recipe list
			recipeList[recipeListIndex] = newRecipe;
			recipeListIndex++;
		}
		
		recipeListIndex = 0;
		scanner.close();
		
		//Prints each recipe
		for(int b = 1; b <= recipeList.length; b++) {
				
			System.out.println("Recipe # " + b);
			recipeList[b - 1].printConvertedList();
		}
		
		
	}
	
	public class Recipe{
		private int portionNumber;
		private int desiredPortionNumber;
		private float mainIngredientWeight;
		private Ingredient[] ingredientList;
		private int ingredientIndex = 0;
		
		private float scaledWeight;
		
		public Recipe(int ingredientNumber, int portionNumber, int desiredPortionNumber) {
			ingredientList = new Ingredient[ingredientNumber];
			this.portionNumber = portionNumber;
			this.desiredPortionNumber = desiredPortionNumber;
		}
		
		public void setMainIngredientWeight(float weight) {
			mainIngredientWeight = weight;
		}
		
		public int getPortionNumber() {
			return portionNumber;
		}
		
		public int getDesiredPortionNumber() {
			return desiredPortionNumber;
		}
		
		public float getMainIngredientWeight() {
			return mainIngredientWeight;
		}
		
		public void addIngredient(Ingredient ingredient) {
			ingredientList[ingredientIndex] = ingredient;
			ingredientIndex++;
		}
		
		public int getIngredientListLength() {
			return ingredientList.length;
		}
		
		public void printConvertedList() {

			//Calculates scaled weight for conversion
			scaledWeight = ((float) desiredPortionNumber / (float) portionNumber) * mainIngredientWeight;
			
			//Prints each ingredient in the list
			for(int i = 0; i < ingredientList.length; i++) {
				float convertedWeight = (ingredientList[i].getPercentage() / 100 ) * scaledWeight;
				
				
				
				String convertedText = ingredientList[i].getName() + " " + String.format("%.1f", convertedWeight);
				convertedText = convertedText.replaceAll(",", ".");
				System.out.println(convertedText);
//				System.out.println(ingredientList[i].getName() + " " + String.format("%.1f", convertedWeight));
			}
			
			System.out.println("----------------------------------------");
		}
		
	}
	
	
	
	public class Ingredient {
		private String name;
		private float weight;
		private float percentage;
		
		
		public Ingredient(String name, float weight, float percentage) {
			this.name = name;
			this.weight = weight;
			this.percentage = percentage;
		}
		
		public String getName() {
			return name;
		}
		
		public float getWeight() {
			return weight;
		}
		
		public float getPercentage() {
			return percentage;
		}
	}
	
	
}
