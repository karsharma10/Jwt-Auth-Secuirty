import "./App.css"
import Header from "./Header";
import AppContent from "./AppContent";
export default function App(){
    return(
        <div>
            <Header pageTitle = "Fronend authenticated with JWT"></Header>
            <div>
                <AppContent/>
            </div>
        </div>
    )
}