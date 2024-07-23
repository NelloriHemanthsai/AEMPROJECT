document.addEventListener('DOMContentLoaded', function() {
    const video = document.getElementById('my-video');
    const downloadBtn = document.getElementById('download-pdf');

    // Play/pause video on click
    video.addEventListener('click', function() {
        if (video.paused) {
            video.play();
        } else {
            video.pause();
        }
    });

    // Download PDF function
    downloadBtn.addEventListener('click', function() {
        downloadPDF();
    });

    function downloadPDF() {
        // Replace this with your actual PDF generation/download logic
        console.log('Downloading PDF...');
        // Simulating a download link
        window.location.href = 'D:\aem-hybrid-architecture-wp-1-18-19.pdf';
    }
});

