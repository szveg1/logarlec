package pass.model;

public interface Idozitett {
    default void tick(){
        CustomLogger.info("Idozitett tick");
    }
}
