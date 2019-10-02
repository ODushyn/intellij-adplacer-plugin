import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.vfs.VirtualFile
import org.apache.commons.lang.StringUtils

class HelloAction : AnAction("Hello") {

    private val AD_WIDTH = 5
    private val AD_HEIGHT = 10

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        //Messages.showMessageDialog(project, "Hello world!", "Greeting", Messages.getInformationIcon())
        val editor = e.getData(CommonDataKeys.EDITOR)


        val offset = editor?.caretModel?.offset
        println(offset)


        val vir: VirtualFile? = e.getData(CommonDataKeys.VIRTUAL_FILE)
        //println((vir?.contentsToByteArray()?.let { String(it) }))
        checkAdPlaceAvailability(vir)

        vir?.contentsToByteArray()?.let { String(it) }!!.split("\n").forEachIndexed { index, line ->
            if (isLineAdable(line)) {
                val lineOffset = editor?.document?.getLineStartOffset(index)
                WriteCommandAction.runWriteCommandAction(project) {
                    editor?.document?.insertString(lineOffset!!, "/*  _   _  ___  _   _ _ __  */")
                    editor?.document?.replaceString(
                        lineOffset!! + 31,
                        lineOffset!! + 61,
                        ""
                    )
                }
            }
        }

//        vir?.let {
//            LoadTextUtil.loadText(it).asIterable().forEachIndexed { index, el ->
//                print(editor!!.document.getLineStartOffset(index))
//                print((el.toString()))
//            }
//        }
        //vir?.contentsToByteArray()?.let { String(it).split("\n").forEach(::print) }

    }

    fun checkAdPlaceAvailability(vir: VirtualFile?) {
        var availableAdHeight = 0
        vir?.contentsToByteArray()?.let { String(it) }!!.split("\n").forEachIndexed { index, line ->
            if (isLineAdable(line)) {
                availableAdHeight++
            } else {
                availableAdHeight = 0
            }

            if (availableAdHeight == AD_HEIGHT) {
                println("ADD AD!")
            }
        }
    }

    fun isLineAdable(s: String): Boolean {
        return StringUtils.isEmpty(s.trim()) || s.indexOf(s.trim()) > AD_WIDTH
    }
}
