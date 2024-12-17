document.addEventListener("DOMContentLoaded", () => {
    const manageCandidatesTab = document.getElementById("manageCandidatesTab");
    const manageEmployersTab = document.getElementById("manageEmployersTab");
    const manageCandidates = document.getElementById("manageCandidates");
    const manageEmployers = document.getElementById("manageEmployers");

    if (manageCandidates && manageCandidates.classList.contains("active")) {
        // Cập nhật trạng thái của menu sidebar
        document.querySelector(".sidebar li.active").classList.remove("active");
        manageCandidatesTab.parentElement.classList.add("active");
    } else if (manageEmployers && manageEmployers.classList.contains("active")) {
        // Cập nhật trạng thái của menu sidebar
        document.querySelector(".sidebar li.active").classList.remove("active");
        manageEmployersTab.parentElement.classList.add("active");
    }
});