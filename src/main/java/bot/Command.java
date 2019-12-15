package bot;

import bot.dataClasses.UserInfo;

public abstract class Command {
    protected static TgManager manager = new TgManager();
    public final String name;
    public final String description;

    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * Метод, который будет вызываться для исполнения команды
     * @param message сообщение пользователя
     */
    public abstract void exec(String message, UserInfo userInfo);

    /**
     * Возвращает строку в формате:<br>
     * name: имяКоманды<br>
     *
     * @return форматированное имя и мод команды
     */

    @Override
    public String toString() {
        return String.format("%s - %s", this.name, this.description);
    }

    /**
     * Берет хэш-код значащего поля {@link #name}
     *
     * @return хэш-код команды
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Объекты эквивалентны только, если поля <code>{@link #name}</code> равны
     * имеют одинаковое значение и объект является классом-наследником {@link Command}
     * @param obj сравниваемый объект
     * @return {@code true} если объекты эквивалентны; {@code false} если объекты различаются
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Command){
            if (name.equals(((Command) obj).name)){
                return true;
            }
        }
        return false;
    }
}