import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.vfs.VirtualFile

class HelloAction : AnAction("Hello") {

    override fun actionPerformed(e: AnActionEvent) {
        val project  = e.project
        //Messages.showMessageDialog(project, "Hello world!", "Greeting", Messages.getInformationIcon())
        val editor = e.getData(CommonDataKeys.EDITOR);


        val offset = editor?.caretModel?.offset
        println(offset)

        WriteCommandAction.runWriteCommandAction(project) {editor?.document?.insertString(offset!!, "Hello")}
        WriteCommandAction.runWriteCommandAction(project) {editor?.document?.replaceString(offset!! + 6, offset!! + 10,"")}

        val vir: VirtualFile? = e.getData(CommonDataKeys.VIRTUAL_FILE)
        //println((vir?.contentsToByteArray()?.let { String(it) }))
        //vir?.let { LoadTextUtil.loadText(it).asIterable().forEach( ::print ) }
        vir?.contentsToByteArray()?.let { String(it).split("\n").forEach(::print) }
    }
}