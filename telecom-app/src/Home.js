
import ImageSlider from "./ImageSlider";

export default function Home() {
  const images = [
    "/images/IS1.jpg",
    "/images/IS2.png",
    "/images/IS3.png",
    "/images/IS4.png",
    "/images/IS5.png",
    "/images/IS6.png",
    // Add more image URLs as needed
  ];
  return (
    <div>
      <div className="imageslider">
          <ImageSlider images={images} glideDuration={5000} />
      </div>
    </div>
  );
}
